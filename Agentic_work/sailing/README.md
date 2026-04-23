# Coastal Wind 35 — Sailship Blueprint Package

## Vessel Overview

**Design name:** "Coastal Wind 35"  
**Type:** 10.5 m centre-cockpit GRP sloop — coastal/offshore cruiser  
**Crew:** 4 persons (2 double berths + settee berths)  
**Interior headroom:** 1,950 mm (clear under beams) — comfortable for a 180 cm person  

### Key Design Features
- **Bulb keel:** Classic fin keel with streamlined lead bulb at tip — high righting moment with shallow draft  
- **Centre cockpit:** Cockpit placed amidships, leaving the entire stern for aft cabins and deployable solar panels  
- **Port aft cabin:** Owner's cabin, seamlessly extending from the main saloon; no mandatory separation  
- **Starboard aft:** Engine room + toolbox compartment with direct saildrive access from above  
- **Electric propulsion:** Saildrive-mounted electric motor (recommended over shaft drive — see calculations)  
- **Bow thruster:** 120 mm tunnel thruster, electric, submerged forward  
- **Anchor locker:** Generous bow locker holding ≥ 80 m chain + propane/butane cylinders  
- **Bathing platform:** Folding/fixed platform aft of the transom, low to water  
- **Solar panel area:** Stern arch/frame above bathing platform for deployable or fixed panels  

---

## Principal Dimensions

| Parameter | Full Scale | 1:11 Scale Model |
|-----------|-----------|-----------------|
| LOA | 10,500 mm | 955 mm |
| LWL | 9,000 mm | 818 mm |
| Beam (max) | 3,400 mm | 309 mm |
| Beam at WL | 2,900 mm | 264 mm |
| Draft (incl. bulb) | 1,950 mm | 177 mm |
| Fin keel height | 1,050 mm | 95 mm |
| Bulb depth | 200 mm | 18 mm |
| Hull depth at keel root | 700 mm | 64 mm |
| Freeboard (bow) | 1,400 mm | 127 mm |
| Freeboard (midship) | 1,100 mm | 100 mm |
| Freeboard (stern deck) | 1,200 mm | 109 mm |
| Coachroof height above deck | 850 mm | 77 mm |
| Interior headroom (sole to beam) | 1,950 mm | — |
| Mast above deck | 16,500 mm | 1,500 mm |
| I (forestay height) | 15,500 mm | 1,409 mm |
| J (forestay base from mast) | 4,500 mm | 409 mm |
| P (main luff) | 16,000 mm | 1,454 mm |
| E (main foot) | 5,500 mm | 500 mm |
| Mainsail area | 44.0 m² | 0.364 m² |
| 100% foretriangle area | 34.9 m² | 0.289 m² |
| Total working sail area | 78.9 m² | 0.653 m² |
| Displacement | 8,500 kg | 6.39 kg |
| Ballast (bulb + fin) | 3,000 kg | 2.25 kg |
| Ballast ratio | 35.3 % | — |
| Electric motor (saildrive) | 15 kW continuous | — |
| Battery bank | 25 kWh LiFePO4 | — |
| Bow thruster | 3 kW / 120 mm tunnel | — |

---

## File Index

### Text Documents
| File | Description |
|------|-------------|
| `calculations_and_notes.md` | Hydrostatics, stability, sail performance, structural loads, electric propulsion sizing, bow thruster, anchor locker volumes |
| `builders_tips_and_tricks.md` | Practical guide: construction sequence, GRP, keel casting, electric install, centre-cockpit details, model testing |

### Full-Scale Blueprints (real dimensions in mm, print at 1:20 for A1)
| File | Description |
|------|-------------|
| `full_scale_lines_plan.svg` | Hull lines: profile, half-breadth plan, and body plan (10 stations) |
| `full_scale_sail_plan.svg` | Sail & rigging plan: mast, boom, forestay, backstay, shrouds, sail shapes, CE/CLR markers |
| `full_scale_deck_plan.svg` | Deck layout: cockpit, hatches, winches, anchor locker, bow thruster, solar arch, bathing platform |
| `full_scale_interior_layout.svg` | Interior arrangement: owner's cabin (port), engine/toolbox (stbd), saloon, galley, nav station, fwd cabin |
| `full_scale_construction_details.svg` | Structural details: bulb keel bolt pattern, saildrive installation, bow thruster tunnel, mast step, chainplates, bulkheads |

### 1:11 Scale Model Blueprints
| File | Description |
|------|-------------|
| `model_1_11_lines_plan.svg` | Lines plan at 1:11 scale — model LOA ≈ 955 mm |
| `model_1_11_sail_plan.svg` | Sail plan at 1:11 scale |
| `model_1_11_construction.svg` | Model construction: frame positions, keel ballast weight, saildrive stub |

---

## Scale Note: 1:11 Model
| Quantity | Scale factor | Example |
|----------|-------------|---------|
| Length | ÷ 11 | 10,500 mm → 955 mm |
| Area | ÷ 121 | 78.9 m² → 0.652 m² |
| Volume / Displacement | ÷ 1,331 | 8,500 kg → 6.39 kg |

The model should float at its design waterline when ballasted to 6.39 kg total weight (≈ 2.25 kg lead keel).

---

## Propulsion Decision: Saildrive vs Shaft Drive

**Recommendation: Saildrive** — reasons:
1. Compact installation — electric motor integrates directly with the saildrive leg below hull
2. No shaft, cutless bearing, or P-bracket required
3. Pod is steerable in some designs (better low-speed manoeuvring)
4. Lower noise and vibration
5. Products specifically designed for electric saildrive: **Oceanvolt ServoProp 10/15**, **Torqeedo Cruise 10.0 RS**
6. Shaft drive is still valid if the keel geometry or hull shape makes saildrive placement difficult — see construction details SVG

---

## Construction Materials
| Component | Material |
|-----------|----------|
| Hull laminate | E-glass + vinylester resin, Divinycell H80 sandwich core (topsides) |
| Keel | Cast lead (Pb) bulb + mild steel fin (or full lead fin) |
| Keel bolts | M36 Aquamet 22 stainless, 8 bolts |
| Deck | GRP sandwich, 12 mm Divinycell core |
| Bulkheads | 18 mm marine ply, GRP tabbed both faces |
| Mast | 6061-T6 anodised aluminium extrusion |
| Standing rigging | 1×19 SS wire or Dyneema rod |
| Bow thruster | 3 kW brushless DC in 120 mm GRP tube |
| Electric motor | Oceanvolt AXC 15 or equivalent (15 kW cont.) |
| Battery | LiFePO4 prismatic cells, 25 kWh, 48V system |

---

*Coastal Wind 35 — Design Package v1.0 — Agentic_work/sailing/*
