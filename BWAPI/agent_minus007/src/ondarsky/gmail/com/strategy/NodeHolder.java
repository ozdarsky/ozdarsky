package ondarsky.gmail.com.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import bwapi.Game;
import bwapi.Player;
import ondarsky.gmail.com.Log;
import ondarsky.gmail.com.strategy.intf.Demand;
import ondarsky.gmail.com.strategy.intf.EntryPoint;
import ondarsky.gmail.com.strategy.intf.Node;
import ondarsky.gmail.com.strategy.nodes.demands.Drone;
import ondarsky.gmail.com.strategy.nodes.demands.Extractor;
import ondarsky.gmail.com.strategy.nodes.demands.Gas;
import ondarsky.gmail.com.strategy.nodes.demands.Minerals;
import ondarsky.gmail.com.strategy.nodes.demands.Overlord;
import ondarsky.gmail.com.strategy.nodes.demands.SpawningPool;
import ondarsky.gmail.com.strategy.nodes.entries.AvailableDrone;
import ondarsky.gmail.com.strategy.nodes.entries.IdleDrone;
import ondarsky.gmail.com.strategy.nodes.entries.IdleLarva;
import ondarsky.gmail.com.strategy.nodes.executables.DroneToMorp;
import ondarsky.gmail.com.strategy.nodes.executables.ExtractorLocation;
import ondarsky.gmail.com.strategy.nodes.executables.ExtractorToMorp;
import ondarsky.gmail.com.strategy.nodes.executables.FindGasField;
import ondarsky.gmail.com.strategy.nodes.executables.FindMineralField;
import ondarsky.gmail.com.strategy.nodes.executables.Harvest;
import ondarsky.gmail.com.strategy.nodes.executables.OverlordToMorp;
import ondarsky.gmail.com.strategy.nodes.executables.SpawningPoolLocation;
import ondarsky.gmail.com.strategy.nodes.executables.SpawningPoolToMorp;

public class NodeHolder {

	private static Log log = Log.getInstance();

    private static List<EntryPoint> entryPoints = new ArrayList<>();
    private static List<Demand> demands = new ArrayList<>();
    private static List<Node> allNodes = new ArrayList<>();

    private static Transition[][] nodeMap;

    public static void initialize(Game game, Player player) {
    	log.info("in initialize");

    	Stream.of(
    			new Drone(),
    			new Gas(),
    			new Minerals(),
    			new SpawningPool(),
    			new IdleDrone(),
    	    	new IdleLarva(),
		    	new DroneToMorp(),
		    	new FindGasField(),
		    	new FindMineralField(),
		    	new Harvest(),
		    	new AvailableDrone(),
		    	new SpawningPoolToMorp(),
		    	new SpawningPoolLocation(),
		    	new Overlord(),
		    	new OverlordToMorp(),
		    	new Extractor(),
				new ExtractorLocation(),
				new ExtractorToMorp())
    	.forEach(n -> NodeHolder.initializeNode(n, game));

        nodeMap = new Transition[allNodes.size()][allNodes.size()];

        // ready larvaes
        initializeLarvas(entryPoints, demands, nodeMap);
        // ready drones
        initializeDrones(entryPoints, demands, nodeMap);

        for(int i = 0; i < allNodes.size(); i++) {
        	System.out.print("[");
        	for(int j = 0; j < allNodes.size(); j++) {
        		System.out.print(nodeMap[i][j] + ", ");
        	}
        	System.out.println("]");
        }
    }

    /**
     * Readies all command nodes and stuff them in appropriate queue
     */
    private static void initializeNode(Node node, Game game) {
    	if (node instanceof Demand) {
    		demands.add((Demand) node);
    		log.info(String.format("Inicializovan novy pozadavek %s", node.toString()));
    	} else if (node instanceof EntryPoint) {
    		entryPoints.add((EntryPoint) node);
    		log.info(String.format("Inicializovan novy vstupni bod %s", node.toString()));
    	}
    	allNodes.add(node);
    	node.initialize(game, allNodes.size() - 1);
    	log.info(node + " initialized with ID " + node.getId());
    }

    /**
     * Retrieve node bysed on its identification number
     */
	public static Node findNodeByID(final int id) {
		return allNodes.stream()
			.filter(node -> node.getId() == id)
			.findFirst().orElse(null);
	}

	public static Transition[][] getNodeMap() {
		return nodeMap;
	}

	public static List<EntryPoint> getEntryPoints() {
		return entryPoints;
	}

	public static List<Demand> getDemands() {
		return demands;
	}

	/**
	 * Pripravi prechody pro Larvy
	 */
	private static void initializeLarvas(List<EntryPoint> entryPoints2, List<Demand> demands2, Transition[][] nodeMap2) {
		nodeMap[IdleLarva.ID][Drone.ID] = new Transition(IdleLarva.ID).extendPath(DroneToMorp.ID).extendPath(Drone.ID);
		nodeMap[IdleLarva.ID][Overlord.ID] = new Transition(IdleLarva.ID).extendPath(OverlordToMorp.ID).extendPath(Overlord.ID);
	}

	/**
	 * Pripravi prechody pro Drony
	 */
	private static void initializeDrones(List<EntryPoint> entryPoints2, List<Demand> demands2, Transition[][] nodeMap2) {
		nodeMap[IdleDrone.ID][Minerals.ID] = new Transition(IdleDrone.ID).extendPath(FindMineralField.ID).extendPath(Harvest.ID).extendPath(Minerals.ID);
		nodeMap[IdleDrone.ID][Gas.ID] = new Transition(IdleDrone.ID).extendPath(FindGasField.ID).extendPath(Harvest.ID).extendPath(Gas.ID);
		nodeMap[AvailableDrone.ID][SpawningPool.ID] = new Transition(AvailableDrone.ID).extendPath(SpawningPoolLocation.ID).extendPath(SpawningPoolToMorp.ID).extendPath(SpawningPool.ID);
		nodeMap[AvailableDrone.ID][Extractor.ID] = new Transition(AvailableDrone.ID).extendPath(ExtractorLocation.ID).extendPath(ExtractorToMorp.ID).extendPath(Extractor.ID);
	}
}
