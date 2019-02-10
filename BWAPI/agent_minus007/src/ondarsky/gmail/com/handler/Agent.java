package ondarsky.gmail.com.handler;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Order;
import bwapi.Player;
import bwapi.Position;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.Log;
import ondarsky.gmail.com.strategy.NodeHolder;
import ondarsky.gmail.com.strategy.Transition.Step;
import ondarsky.gmail.com.strategy.intf.Demand;
import ondarsky.gmail.com.strategy.intf.ExecutableOrder;
import ondarsky.gmail.com.strategy.intf.Node;
import ondarsky.gmail.com.utils.StepResults;

public class Agent extends DefaultBWListener {
    private Mirror mirror = new Mirror();
    private Game game;
    private Player self;

    private static Log log = Log.getInstance();
    Map<UnitType, String> orders = new HashMap<>();

    /**
     * Runs the bot
     */
    public static void main(String[] args) {
    	int logLevel = 0;
    	if(args.length > 0) {
    		logLevel = Integer.parseInt(args[0]);
    	}
    	log.setup(logLevel);
        new Agent().run();
    }

    /**
     * Start new thread and handle stuff required for this agent
     */
    public void run() {
        mirror.getModule().setEventListener(this);
        System.out.println("START Crapola Inc.");
        mirror.startGame();
    }

    @Override
    public void onStart() {
        game = mirror.getGame();
        self = game.self();
        try {
			NodeHolder.initialize(game, self);
		} catch (Exception e) {
			System.out.println("Game inicialization failed");
			e.printStackTrace();
		}

        // Use BWTA to analyze map
        // This may take a few minutes if the map is processed first time!
//        log.debug("Analyzing map...");
//        BWTA.readMap();
//        BWTA.analyze();
//        log.debug("Map data ready");
//        BWTA.getBaseLocations().stream()
//                .forEach(i -> System.out.println(String.format("Base at %d:%d", i.getX(), i.getY())));
    }

	@Override
    public void onFrame() {
		self.getUnits().stream()
			.forEach(unit -> {
				try {
					NodeHolder.getDemands().stream()
						.filter(d -> d.isApplicableForUnit(unit))
						.peek(Demand::starvePriority)	// recalculate priorities of this demand
				        .sorted(Comparator.comparingInt(Demand::getPriority))	// then sort them
				        .findFirst()
				        .ifPresent(request -> {
							// notice on what this unit will be doing
							orders.put(unit.getType(), request.toString());

						    NodeHolder.getEntryPoints().stream()
						    	.filter(ep -> ep.qualify(unit))
						    	.map(ep -> NodeHolder.getNodeMap()[ep.getId()][request.getId()])
						    	.filter(Objects::nonNull)
						    	.findFirst()
						    	.ifPresent(decision -> {
						    		Step p = decision.getPath();

						    		StepResults maybeTarget = StepResults.empty();
						    		int traversalCost = 0;

						    		while (p != null) {
						    			Node curr = p.getCurrent();
						    			traversalCost += curr.getTraversalCost();

						    			if (curr instanceof ExecutableOrder) {
						    				ExecutableOrder exec = (ExecutableOrder) curr;
						    				if (exec.isApplicable(unit, maybeTarget.getPositionOpt())) {
						    					// perform pipelining of targets
						    					maybeTarget = exec.execute(unit, maybeTarget.getUnitOpt(), maybeTarget.getPositionOpt());
						    				}
						    			} else if (curr instanceof Demand && maybeTarget.isPresent()) {
						    				// decay priority if command chain has been completed
						    				((Demand) curr).decayPriority(traversalCost);
						    			}
						    			p = p.getNext();
						    		}
						    	});
				        });

				    Position unitPos = unit.getPosition();
				    Position orderPos = unit.getOrderTargetPosition();
				    if (orderPos.getX() > 0 && (unit.getOrder() != Order.Larva || unit.getOrder() != Order.Guard)) {
				    	game.drawTextMap(unitPos.getX(), unitPos.getY(), unit.getOrder().toString());
				    	game.drawLineMap(unitPos.getX(), unitPos.getY(),
				    			orderPos.getX(), orderPos.getY(),
				    			bwapi.Color.Green);
				    }
				} catch (Exception e) {
					System.err.println("-------- CHYBA ---------");
					Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString).forEach(System.err::println);
				}
			});

        game.drawTextScreen(10, 15, "APM: " + game.getAPM());

        int i = 25;
        Iterator<Entry<UnitType, String>> iter = orders.entrySet().iterator();
        while (orders.entrySet().iterator().hasNext()) {
        	Entry<UnitType, String> r = iter.next();
        	i += 10;
    		game.drawTextScreen(10, i, r.getKey() + ": " + r.getValue());
    	}
    }

    @Override
    public void onUnitComplete(Unit unit) {
    	if (unit.getType().equals(UnitType.Zerg_Overlord)) {
    		NodeHolder.getDemands().stream()
    			.filter(d -> ondarsky.gmail.com.strategy.nodes.demands.Overlord.ID == d.getId())
    			.findFirst()
    			.ifPresent(d -> d.decayPriority(-16));
    	}
        super.onUnitComplete(unit);
    }

    @Override
    public void onUnitCreate(Unit unit) {
    	// TODO Auto-generated method stub
    	super.onUnitCreate(unit);
    }

    @Override
    public void onEnd(boolean arg0) {
        // TODO Auto-generated method stub
        super.onEnd(arg0);
    }

    @Override
    public void onUnitDiscover(Unit unit) {
    	// TODO Auto-generated method stub
    	super.onUnitDiscover(unit);
    }
}