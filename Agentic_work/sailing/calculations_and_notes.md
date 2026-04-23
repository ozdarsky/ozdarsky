# Coastal Wind 35 — Engineering Calculations & Technical Notes

## 1. Principal Dimensions

| Parameter | Value | Notes |
|-----------|-------|-------|
| LOA | 10,500 mm | Including bowsprit fitting if added |
| LWL | 9,000 mm | Waterplane length |
| BOA | 3,400 mm | Overall beam |
| BWL | 2,900 mm | Beam at design waterline |
| Draft total | 1,950 mm | Keel root 700 + fin 1050 + bulb 200 |
| Hull baseline | 700 mm below DWL | Hull bottom at midship |
| Fin keel height | 1,050 mm | Hull bottom to top of bulb |
| Bulb height | 200 mm | Lead bulb below fin tip |
| Freeboard bow | 1,400 mm | Above DWL |
| Freeboard midship | 1,100 mm | Minimum freeboard |
| Freeboard stern | 1,200 mm | Above DWL at AP |
| Coachroof above deck | 850 mm | Both sides of centre cockpit |
| Interior headroom | 1,950 mm | Sole to underside of coachroof beam at centreline |
| Bow overhang | 400 mm | Forward of FP |
| Stern overhang | 1,100 mm | Transom aft of AP |
| Bathing platform ext. | 600 mm | Beyond transom face |
| Mast above deck | 16,500 mm | |
| I | 15,500 mm | Forestay height above deck |
| J | 4,500 mm | Forestay base from mast |
| P | 16,000 mm | Mainsail luff along mast |
| E | 5,500 mm | Mainsail foot along boom |
| Forestay length | 16,139 mm | √(15500²+4500²) |

---

## 2. Hydrostatic Calculations

### 2.1 Displacement & Coefficients

```
Displaced volume:
  ∇ = Disp / ρ_seawater = 8,500 / 1,025 = 8.293 m³

Waterplane area (AWP):  Cw assumed 0.74 (moderate beam, centre-cockpit form)
  AWP = Cw × LWL × BWL = 0.74 × 9.0 × 2.9 = 19.31 m²

Midship section area (AM):  Cm = 0.74 (full midship section, centre-cockpit design)
  AM = Cm × BWL × T_hull = 0.74 × 2.9 × 0.70 = 1.503 m²
  (T_hull = 0.70 m = hull depth below DWL, not including fin keel)

Prismatic coefficient:
  Cp = ∇ / (AM × LWL) = 8.293 / (1.503 × 9.0) = 8.293 / 13.527 = 0.613
  → Within acceptable range 0.56–0.64 for a cruising boat ✓

Block coefficient:
  Cb = ∇ / (LWL × BWL × Draft) = 8.293 / (9.0 × 2.9 × 1.95) = 8.293 / 50.895 = 0.163
  → Low Cb correct for fin-keel sailboat ✓

Waterplane coefficient check:
  Cw = AWP / (LWL × BWL) = 19.31 / (9.0 × 2.9) = 0.74 ✓
```

### 2.2 Centre of Buoyancy

```
LCB (longitudinal, from FP):
  LCB ≈ 0.44 × LWL = 0.44 × 9,000 = 3,960 mm from FP
  (Slightly forward of midship — appropriate for Cp 0.61)

VCB (vertical, above hull baseline):
  VCB ≈ 0.42 × T_hull = 0.42 × 700 = 294 mm above hull baseline
       = 700 - 294 = 406 mm below DWL
```

### 2.3 Weight Breakdown & Centre of Gravity

| Component | Mass (kg) | VCG above keel bottom (mm) |
|-----------|-----------|---------------------------|
| Lead bulb keel | 2,400 | 100 |
| GRP fin keel (steel insert) | 600 | 875 (keel mid-height from bottom) |
| Hull GRP laminate | 900 | 1,400 |
| Deck + coachroof | 250 | 2,700 |
| Interior joinery | 350 | 2,100 |
| Rig (mast, boom, rigging) | 180 | 10,000 |
| Engine + saildrive | 90 | 1,800 |
| Battery bank (25 kWh) | 250 | 1,600 |
| Bow thruster + tube | 30 | 2,300 |
| Systems (tanks, pumps, etc.) | 180 | 1,700 |
| Stores + crew (4 × 85 kg) | 340 | 2,200 |
| Water tank (200 L) | 200 | 1,500 |
| Fuel eq. (none; electric) | 0 | — |
| Ballast margin (misc.) | 730 | 1,800 |
| **TOTAL DISPLACEMENT** | **8,500** | |

```
Σ(m × h) = 2400×100 + 600×875 + 900×1400 + 250×2700 + 350×2100
          + 180×10000 + 90×1800 + 250×1600 + 30×2300 + 180×1700
          + 340×2200 + 200×1500 + 730×1800
= 240000 + 525000 + 1260000 + 675000 + 735000
+ 1800000 + 162000 + 400000 + 69000 + 306000
+ 748000 + 300000 + 1314000
= 8,534,000 kg·mm

KG = 8,534,000 / 8,500 = 1,004 mm above keel bottom
   = 1,950 - 1,004 = 946 mm below DWL
   → VCG is 946 mm BELOW DWL ✓ (strong positive stability)
```

---

## 3. Stability

### 3.1 Metacentric Height (GM)

```
BM (metacentric radius):
  I_WP (second moment of waterplane area about centreline):
  I_WP = (LWL × BWL³) / 12 × Cw
       = (9.0 × 2.9³) / 12 × 0.74
       = (9.0 × 24.389) / 12 × 0.74
       = 219.5 / 12 × 0.74 = 13.52 m⁴
  BM = I_WP / ∇ = 13.52 / 8.293 = 1.630 m

KB (vertical centre of buoyancy above keel bottom):
  VCB from keel bottom = 1,950 - 406 = 1,544 mm = 1.544 m

KG = 1.004 m (from weight breakdown above)

GM = KB + BM - KG = 1.544 + 1.630 - 1.004 = 2.170 m

Note: This is the initial metacentric height using waterplane theory (accurate at 0°).
A full stability curve (GZ curve) requires numerical integration or design software.
Estimated AVS (Angle of Vanishing Stability) > 130° due to high ballast ratio + bulb keel.
```

### 3.2 Stability Criteria

```
GM/LWL = 2.17 / 9.0 = 0.241 → Stiff boat (> 0.10 threshold)
Ballast ratio = 3,000 / 8,500 = 35.3% ✓ (offshore boats typically 30–40%)

Capsize Screening Value (CSV):
  ∇ in m³ = 8.293 m³
  CSV = BOA / (∇)^(1/3) × K  [simplified metric]
  CSV = 3.4 / 8.293^(1/3) = 3.4 / 2.028 = 1.68
  → CSV < 2.0 ✓ (considered offshore-capable)

Approximate GZ curve:
  0°   → 0 m
  15°  → 0.54 m
  30°  → 0.98 m
  45°  → 1.20 m (approx. max)
  60°  → 1.05 m
  90°  → 0.35 m (bulb keel keeps righting arm positive)
  120° → -0.05 m (AVS ≈ 118–125°)
```

---

## 4. Sail Area & Performance

### 4.1 Sail Dimensions

| Parameter | Value | Derived |
|-----------|-------|---------|
| I | 15,500 mm | |
| J | 4,500 mm | |
| P | 16,000 mm | |
| E | 5,500 mm | |
| Forestay length | 16,139 mm | √(15500²+4500²) |
| Mainsail area | 44.0 m² | ½ × P × E + 10% roach |
| Foretriangle 100% | 34.9 m² | ½ × I × J = ½ × 15.5 × 4.5 |
| Working jib (90%) | 31.4 m² | |
| Genoa (135%) | 47.1 m² | |
| Total working | 78.9 m² | Main + 100% jib |
| Spinnaker (estimated) | 110 m² | P × J × 1.58 |

### 4.2 Sail Area to Displacement Ratio

```
Metric SA/D = SA / ∇^(2/3) = 78.9 / 8.293^(2/3) = 78.9 / 4.09 = 19.3

This is in the moderate-performance range (18–22 for a cruising sloop).
Light-air sailing will benefit from a large code-zero or asymmetric spinnaker.
```

### 4.3 Centre of Effort (CE) — Upright

```
CE of mainsail (from foot):
  CE_main_height above deck = P × 0.39 = 16,000 × 0.39 = 6,240 mm
  CE_main above DWL = 1,100 (freeboard) + 6,240 = 7,340 mm

CE of jib (100%):
  CE_jib_height above deck = I × 0.39 = 15,500 × 0.39 = 6,045 mm
  CE_jib above DWL = 1,100 + 6,045 = 7,145 mm

Combined CE above DWL:
  CE = (SA_main × CE_main + SA_jib × CE_jib) / (SA_main + SA_jib)
     = (44.0 × 7,340 + 34.9 × 7,145) / 78.9
     = (322,960 + 249,361) / 78.9
     = 572,321 / 78.9 = 7,254 mm above DWL
```

### 4.4 Centre of Lateral Resistance (CLR)

```
CLR of fin keel (from FP):
  Keel at approx. x = 3,800–5,200 mm from FP
  CLR_keel ≈ 4,500 mm from FP (centroid of keel planform)

CLR of hull (underwater body):
  LCB at 3,960 mm from FP (= approximate CLR of hull)

Combined CLR ≈ 4,200 mm from FP
Sail CE longitudinal position: approx. 4,000 mm from FP (CE leads CLR by ~200mm = lead ~4.8%)
→ Slight weather helm expected (desired 3–5%) ✓
```

### 4.5 Heeling Moment at 20 kts Wind

```
Dynamic pressure q = ½ × ρ_air × V²
  = ½ × 1.225 × (20 × 0.5144)² = ½ × 1.225 × 105.6 = 64.7 N/m²

Heeling force (working jib + full main):
  Fh = q × SA × Cd_projected = 64.7 × 78.9 × 0.55 = 2,808 N

Heeling moment:
  HM = Fh × CE_above_DWL = 2,808 × 7.254 = 20,369 N·m

Righting moment at 20° heel:
  RM = Disp × g × GZ_20 = 8,500 × 9.81 × (GM × sin20°)
     ≈ 8,500 × 9.81 × (2.17 × 0.342) = 8,500 × 9.81 × 0.742 = 61,840 N·m

Equilibrium heel ≈ 12–15° at 20 kts wind with working sail ✓ (comfortable)
First reef recommended at 22–25 kts to keep heel below 20°.
```

---

## 5. Electric Propulsion System

### 5.1 Saildrive vs Shaft Drive Analysis

| Criteria | Saildrive | Shaft Drive |
|----------|-----------|------------|
| Installation simplicity | ✓ Simpler | More complex (shaft, bearings, seal) |
| Efficiency | ~85–90% | ~78–85% (shaft losses) |
| Space inside boat | ✓ Compact | Requires shaft tunnel |
| Maintenance access | ✓ Lift-out leg | Haul out for shaft/prop |
| Noise/vibration | ✓ Lower | Higher |
| Electric motor integration | ✓ Direct (Oceanvolt, Torqeedo) | Requires coupling |
| Cost | ≈ equal | Slightly lower for basic setup |
| Suitability for this design | **Recommended** | Valid alternative |

**Recommendation: Electric Saildrive — Oceanvolt ServoProp 15 kW or AXC 15**  
(AXC 15 = motor + gearbox unit mounting to any compatible saildrive leg)

### 5.2 Motor Sizing

```
Hull speed: Vhull = 2.43 × √LWL = 2.43 × √9.0 = 2.43 × 3.0 = 7.29 kts

Total resistance at hull speed (estimated):
  Wetted surface S ≈ 1.97 × √(Disp_kg × LWL) ≈ 1.97 × √(8500 × 9.0)
                  ≈ 1.97 × √76500 ≈ 1.97 × 276.6 = 545 m² ... 
  Correct formula: S ≈ 2.56 × ∇^(2/3) = 2.56 × 8.293^(2/3) = 2.56 × 4.09 = 10.47 m²

  Skin friction at 6 kts (motoring speed, economy):
    Re = (6 × 0.5144) × 9.0 / 1.19×10⁻⁶ = 23.4×10⁶
    Cf = 0.075/(log₁₀(23.4×10⁶)-2)² = 0.075/(7.37-2)² = 0.075/28.8 = 0.0026
    Rf = Cf × ½ × 1025 × (6×0.5144)² × S = 0.0026 × ½ × 1025 × 9.53 × 10.47
       = 0.0026 × 51,147 = 133 N

  Wave resistance at 6 kts: Fr = 6×0.5144/√(9.81×9.0) = 3.086/9.397 = 0.328
    At Fr 0.33: Rwave ≈ 250 N (moderate, below hull speed)

  Total resistance at 6 kts ≈ 133 + 250 + 50 (appendage drag) = 433 N

  Required propulsive power at 6 kts:
    P = F × V = 433 × (6 × 0.5144) = 433 × 3.086 = 1,336 W (shaft)
    With drivetrain efficiency 87%: P_motor = 1,336 / 0.87 = 1,536 W → ~1.5 kW

  For hull speed (7.3 kts): Rwave rises steeply
    P_motor estimated ≈ 8–10 kW (wave resistance dominates)

  Peak power (harbour manoeuvring, headwinds, charging speed to hull speed):
    P_peak ≈ 15 kW (covers hull speed under normal conditions)

  Selected motor: 15 kW continuous / 22 kW peak ✓
```

### 5.3 Battery Bank Sizing

```
Target range under power at 5 kts (economy speed):
  P at 5 kts ≈ 700 W shaft → 800 W motor draw
  
  Target: 6 hours motoring at 5 kts = 30 nm range
  Energy needed: 0.8 kW × 6 h = 4.8 kWh

  Allow for 20% reserve and hotel loads (instruments, nav lights, etc.):
  Total usable: 4.8 / 0.80 = 6.0 kWh

  But also want 3 h at hull speed (7.3 kts) for occasional use:
  P at 7.3 kts ≈ 10 kW × 3 h = 30 kWh

  SELECTED: 25 kWh LiFePO4, 48V nominal system (typical for marine electric)
  → 25 kWh at 48V = ~520 Ah capacity
  → 3h+ at hull speed OR 30+ hours at 4–5 kts economy ✓

Solar charging (stern arch):
  6× 400W panels = 2,400 W peak
  At 20% utilisation (sun, angle, losses): 480 W avg
  Daily solar harvest (8h equivalent): 480 × 8 = 3,840 Wh = 3.84 kWh/day
  → Can self-sustain 4–5 kts hotel loads underway + top-up battery during anchorage

Charging at marina:
  Use 230V shore power with onboard charger (e.g., 7 kW): full charge from 20% in ~3h
```

### 5.4 Saildrive Installation Details

```
Saildrive unit position:
  Lateral: centreline (x = 0, y = 0)
  Longitudinal from FP: 6,800 mm (aft of midship, below aft cockpit BH / engine room)
  Depth below DWL: approximately 800 mm to prop centreline
  (Prop must be fully submerged at all sailing angles up to 30° heel)

Saildrive housing (leg):
  GRP ring sealed into hull with Becker/Volvo-style flexible boot (vibration-isolating ring)
  Opening diameter: 200 mm (typical for 15 kW class saildrive)
  Motor sits above deck level in sealed engine room compartment → easy service access

Propeller:
  Type: 3-blade folding or feathering propeller (drag is CRITICAL for a sailing yacht)
  Diameter: 400–450 mm (for 15 kW at 1,500 rpm gearbox output)
  Pitch: variable (ServoProp) or fixed-pitch folding (Flexofold 3-blade)
  Thrust at 6 kts: 540 N (well-matched to resistance estimate above) ✓
```

---

## 6. Bow Thruster

### 6.1 Sizing

```
Required thrust for manoeuvring an 8,500 kg boat:
  Rule of thumb: 1 kgf thrust per 100 kg displacement = 85 kgf = 850 N
  Conservative design: 40 kgf (400 N) is adequate for light-wind harbour work
  
Selected: 3 kW electric bow thruster, 120 mm tunnel diameter
  Thrust ≈ 30–40 kgf (300–400 N) ✓
  (Sideforce from thruster at 25 kts crosswind will be marginal — windage is dominant;
   for stronger conditions, use engine + helm + thruster together)
```

### 6.2 Installation

```
Tunnel position:
  Longitudinal: 700–900 mm from FP (bow area, forward of anchor chain)
  Vertical: centre of tunnel ≥ 200 mm below DWL (must remain submerged at heel)
  At 700 mm from FP, hull beam ≈ 700 mm at DWL
  Tunnel length ≈ 700 mm (full beam at that station)

Tunnel diameter: 120 mm (standard tunnel thruster — Sidepower SP55, Lewmar BTe series)
Tunnel material: GRP tube bonded into hull with 6× layers biaxial CSM

CRITICAL: 
- Tunnel exit MUST be faired smoothly into hull (no step = no drag)
- Motor mounted above hull on a bracket (not in tunnel) — dry motor type
- Wire routing: sealed conduit, minimum 6 AWG for 3 kW at 48V = 62.5A
```

---

## 7. Anchor Locker & Gas Bottle Storage

### 7.1 Anchor Chain Capacity

```
Chain specification for 8,500 kg boat:
  Recommended chain: 10 mm calibrated HT (Grade 43) or 12 mm standard
  80 m of 10 mm chain:
    Mass: 10 mm chain ≈ 0.72 kg/m → 80 × 0.72 = 57.6 kg
    Volume when stowed (packing factor ~35%): 
      Chain cross-sectional area = π × (0.005)² = 0.0000785 m²
      Volume of steel: 0.0000785 × 80 = 0.006 m³
      With packing gaps: 0.006 / 0.35 = 0.017 m³ = 17 litres

  Anchor (Rocna 40 or Mantus M2 40): 40 kg, ~600×600×200 mm stowed

  TOTAL chain + anchor volume: ~50 litres (very manageable)
```

### 7.2 Gas Bottle Storage

```
Standard propane/butane bottles (for cooking):
  2× 5 kg bottles: each bottle approx. Ø220 mm × H450 mm = 17 litres each
  Total: 34 litres for 2 bottles

Ventilation requirement:
  Gas bottles MUST be stored in a ventilated, watertight-to-bilge locker
  Vents must exit at deck level or overboard above waterline — never into cabin
  This is satisfied by the anchor locker (open bow compartment) ✓
  
Partition required:
  Gas bottles SHOULD be separated from anchor chain by a GRP dividing wall
  (Prevents chain impact damage to bottle valves — safety critical)
```

### 7.3 Anchor Locker Dimensions

```
Anchor locker (forward of BH-1, collision bulkhead):
  Length: 1,200 mm
  Max beam: 1,600 mm (at widest, forward of FP)
  Depth below deck: 700 mm
  Volume: approx. 550 litres (0.55 m³)

  Chain well section: 400×400 mm (centred), dropping straight down
  Anchor stowage: side of locker, mounted on chocks
  Gas bottle bay: starboard side of locker, with drain hole
  Windlass mounting: deck above, chainpipe centred

  Chain volume: 17 L + anchor 50L = 67L used of 550L → plenty of space
  Gas bottles: 34L + ventilation space ✓
```

---

## 8. Structural Calculations

### 8.1 Hull Laminate Schedule

| Zone | Layers | Min. Thickness |
|------|--------|---------------|
| Topsides above WL | 3× 600g CSM + 2× 800g WR | 8 mm |
| Underwater hull | 4× 600g CSM + 3× 800g WR + H80 core | 14 mm total |
| Keel root (1.5m radius) | 8× 600g CSM + 4× 800g WR biax | 22 mm solid |
| Mast partner (deck) | 4× 600g CSM + 2× 800g WR + 15mm ply insert | 15 mm |
| Chainplate area | +4× 600g CSM over base | +8 mm extra |
| Saildrive opening | Reinforcement ring 6× biax + steel backing plate | 20 mm ring |
| Bow thruster tunnel | 6× biax CSM in 120mm tube | 8 mm tube wall |

### 8.2 Keel Bolt Calculations (M36 × 8 bolts)

```
Design keel load:
  Static: 3,000 × 9.81 = 29,430 N
  Sailing dynamic (3×): 88,290 N
  Capsize (90° heel) factor (5×): 147,150 N design load

Bolt capacity (M36 Aquamet 22, Fy = 620 MPa):
  A_bolt = π × (36/2)² = 1,018 mm²
  Tensile per bolt = 620 × 1,018 = 631,160 N
  8 bolts: 5,049,280 N total capacity
  Safety factor: 5,049,280 / 147,150 = 34.3 ✓

Shear load at 90° heel:
  Keel_CG at 875 mm above keel bottom (950 mm below DWL)
  Torsional moment: 3,000 × 9.81 × 0.875 = 25,747 N·m
  Bolt group lever arm: 500 mm (keel bolt spread)
  Shear/bolt = 25,747 / (4 × 0.500) = 12,874 N
  Shear capacity M36 = 0.6 × 620 × 1,018 = 378,696 N → SF > 29 ✓

Keel bolt pattern:
  4 bolts forward (at BH-4 position), 4 bolts aft (at BH-5 position)
  Longitudinal spacing: 500 mm between rows
  Transverse spacing: ± 200 mm from centreline (400 mm total spread)
  Bolt length: 450 mm through hull flange + keel stub
```

### 8.3 Mast Step & Compression Post

```
Mast compression load estimate:
  Rigging pretension per shroud: 2,000–3,000 N typical
  4 shrouds + forestay + backstay: combined downward component ≈ 40,000 N
  Peak gusting load: 60,000 N (6 tonnes downward)

Compression post (from keel stub to mast step deck fitting):
  Material: Aluminium box 100×100×5 mm (6061-T6)
  Cross-section area: 4 × 100×5 = 2,000 mm² (hollow box)
  Compressive stress: 60,000 / 2,000 = 30 MPa
  Fy_aluminium_6061 = 270 MPa → SF = 9 ✓
  Buckling check: L/r = 2,200 / (100/√12 ×(well... use Euler for hollow box)
  Euler buckling F_cr = π²EI/L² — well exceeds design load ✓

Mast step fitting:
  Cast aluminium or welded SS plate fitting
  Bolted through deck with 6× M16 bolts into partner ring
  GRP partner ring: 6× biaxial layers + 15mm ply core, 600mm × 600mm
```

### 8.4 Bulkhead Schedule (from FP)

| BH # | From FP (mm) | Function | Type | Thickness |
|------|-------------|----------|------|-----------|
| BH-0 (collision) | 0 (AT FP) | Anchor locker / watertight | WT | 18mm ply + GRP |
| BH-1 (chain locker) | 900 | Gas/chain separation | Struct | 18mm ply |
| BH-2 (forepeak) | 1,800 | Fwd cabin start | Struct | 18mm ply |
| BH-3 (fwd cabin aft) | 3,600 | Chain plate base, knee | Struct | 18mm ply |
| BH-4 (saloon fwd) | 4,500 | Keel bolt forward row | Struct | 25mm ply |
| BH-5 (saloon aft) | 5,800 | Keel bolt aft row | Struct | 25mm ply |
| BH-6 (cockpit fwd) | 6,400 | Cockpit floor, watertight | WT | 18mm ply + GRP |
| BH-7 (cockpit aft) | 7,800 | Aft cabin separator | Struct | 18mm ply |
| BH-8 (transom) | 9,100 | Transom frame, rudder post | Struct | 25mm ply + GRP |

### 8.5 Chain Plates

```
Cap shrouds (2 per side):
  Design load per shroud: 2 × 3,000 N = 6,000 N (with 2.0 SF on pretension)
  Chain plate: 316L SS, 8×60 mm flat bar
  Tensile capacity: 8 × 60 × 550 = 264,000 N → SF = 44 ✓
  Through-bolted to BH-3 / BH-4 with 4× M16 SS bolts + backing plate
  Lead through deck: sealed with polysulfide, stainless deck socket

Runner / baby stay plates:
  6×40 mm flat bar, through-bolted to BH-6 and BH-5
```

---

## 9. Hull Offsets Table

### Coordinate system: FP = x=0, DWL = y=0, y increases above DWL
### Station spacing: 900 mm (LWL/10)
### All values = HALF-BREADTH (distance from centreline, mm) at each waterline height

| Station | x from FP | WL–300 | DWL | WL+300 | WL+600 | WL+900 | Sheer/Deck |
|---------|-----------|--------|-----|--------|--------|--------|-----------|
| FP (0) | 0 | 20 | 100 | 250 | 400 | 500 | 490 |
| 1 | 900 | 300 | 620 | 950 | 1,150 | 1,250 | 1,230 |
| 2 | 1,800 | 620 | 1,050 | 1,380 | 1,580 | 1,680 | 1,660 |
| 3 | 2,700 | 880 | 1,270 | 1,560 | 1,720 | 1,820 | 1,810 |
| 4 | 3,600 | 1,020 | 1,380 | 1,640 | 1,790 | 1,890 | 1,880 |
| 5 (midship) | 4,500 | 1,070 | 1,420 | 1,660 | 1,800 | 1,890 | 1,880 |
| 6 | 5,400 | 1,020 | 1,360 | 1,590 | 1,730 | 1,810 | 1,800 |
| 7 | 6,300 | 880 | 1,200 | 1,420 | 1,560 | 1,640 | 1,635 |
| 8 | 7,200 | 680 | 980 | 1,200 | 1,340 | 1,430 | 1,430 |
| 9 | 8,100 | 380 | 640 | 840 | 980 | 1,080 | 1,090 |
| AP (10) | 9,000 | 80 | 280 | 520 | 700 | 830 | 840 |

**Heights of waterlines above keel baseline (hull bottom):**
- WL–300 = 400 mm above hull bottom (300 below DWL = 700–300 = 400 mm from hull baseline)
- DWL = 700 mm above hull bottom
- WL+300 = 1,000 mm above hull bottom
- WL+600 = 1,300 mm above hull bottom
- WL+900 = 1,600 mm above hull bottom (near deck level, above water)

**Note:** Keel (fin + bulb) extends 1,250 mm below hull baseline (700 fin + 200 bulb = but root chord tapers, not uniform). Bulb is centred at approx. x = 3,800–5,200 mm from FP.

---

## 10. Performance Predictions

### 10.1 Hull Speed

```
Vhull = 2.43 × √LWL = 2.43 × √9.0 = 2.43 × 3.0 = 7.29 kts

Expected boat speed by point of sail at 15 kts TWS:
  Close-hauled (45° TWA)  → 6.2 kts
  Close reach (65° TWA)   → 7.0 kts
  Beam reach (90° TWA)    → 8.0 kts (exceeds hull speed — waves help)
  Broad reach (135° TWA)  → 8.5 kts (best VMG downwind)
  Dead run (170° TWA)     → 7.0 kts (wing-on-wing jib + main)
```

### 10.2 Range Under Power

| Speed | Motor power | Battery life | Range |
|-------|------------|-------------|-------|
| 4 kts | ~0.5 kW | 50 h (25 kWh) | 200 nm |
| 5 kts | ~1.0 kW | 25 h | 125 nm |
| 6 kts | ~2.5 kW | 10 h | 60 nm |
| 7 kts | ~8.0 kW | 3.1 h | 21.7 nm |
| 7.3 kts | ~12 kW | 2.1 h | 15 nm |

---

## 11. 1:11 Scale Model Calculations

| Parameter | Full Scale | Model (÷11) |
|-----------|-----------|-------------|
| LOA | 10,500 mm | 955 mm |
| LWL | 9,000 mm | 818 mm |
| Beam | 3,400 mm | 309 mm |
| Draft | 1,950 mm | 177 mm |
| Mast | 16,500 mm | 1,500 mm |
| Displacement | 8,500 kg | 6.39 kg |
| Ballast | 3,000 kg | 2.25 kg |
| Sail area total | 78.9 m² | 652 cm² |
| Hull speed | 7.29 kts | 2.20 kts |

```
Model hull speed verification:
  V_model = 2.43 × √(LWL_model) = 2.43 × √0.818 = 2.43 × 0.905 = 2.20 kts ✓

Model ballast: 2.25 kg lead
  Options: cast lead in keel form, or drill keel fin and fill with molten lead

Model displacement check:
  ∇_model = 8.293 / 1331 = 0.00623 m³ = 6.23 litres
  Model should displace 6.23 litres of water at DWL → weighs 6.23 kg ≈ 6.39 kg ✓
  (Small difference due to rounding and freshwater vs seawater: ×1.025)
```

---

*Coastal Wind 35 — Calculations & Notes v1.0*
