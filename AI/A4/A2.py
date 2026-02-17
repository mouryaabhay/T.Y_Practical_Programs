# To build a knowledge base for a self-driving car system
# In self-driving cars like those developed by Waymo and Tesla.
# Knowledge about the environment (roads, obstacles, traffic signals, pedestrian behavior) is
# represented in various forms.
# Build a simple knowledge base using production rules to represent road conditions and sensor data:
# Conditions for Road: Speed Limit, Traffic Light (Red, Yellow, Green),Pedestrian Crosswalk
# Conditions for Obstacles: Car, Pedestrian, And Cyclist Rules: If a traffic light is Red, then stop the
# vehicle. Decision Trees help in decision-making: If there's an obstacle ahead, choose Avoid or Stop
# based on sensor data.

def check_traffic_rule(light_status):
    """
    Checks the traffic light condition and returns initial decision
    """
    ls = light_status.lower()

    if ls == "red":
        return "Stop"
    elif ls == "yellow":
        return "Slow Down"
    elif ls == "green":
        return "Go"
    else:
        return "Invalid Traffic Light"

def check_obstacle(obstacle_type):
    """
    Identifies obstacle type and decides action
    """
    ot = obstacle_type.lower()

    if ot == "pedestrian":
        return "Stop"
    elif ot == "car":
        return "Avoid"
    elif ot == "cyclist":
        return "Slow Down"
    elif ot == "none":
        return "No Action"
    else:
        return "Unknown Obstacle"

def decision_tree(light_status, obstacle_type, crosswalk_present):
    """
    Combines road condition and obstacle data to make final driving decision
    """
    ld = check_traffic_rule(light_status)
    od = check_obstacle(obstacle_type)

    # Decision tree logic
    if ld == "Stop":
        return "STOP - Red light detected"

    if ld == "Invalid Traffic Light":
        return "ERROR - Unable to read traffic light, stopping as precaution"

    if od == "Stop":
        if obstacle_type.lower() == "pedestrian":
            return "STOP IMMEDIATELY - Pedestrian detected"
        else:
            return "STOP - Obstacle requires stopping"

    if od == "Avoid":
        return "AVOID - Maneuver around the car"

    if od == "Slow Down":
        if crosswalk_present.lower() == "yes":
            return "SLOW DOWN - Cyclist near crosswalk, proceed with caution"
        else:
            return "SLOW DOWN - Cyclist detected"

    if ld == "Go" and od == "No Action":
        return "MOVE - Clear path, proceed normally"

    if ld == "Slow Down" and od == "No Action":
        return "SLOW DOWN - Yellow light, prepare to stop"

    if ld == "Go" and od == "Unknown Obstacle":
        return "CAUTION - Unknown object detected, slow down"

    return "CAUTION - Proceed with care"

def run_car_decision_system():
    """
    Runs the self-driving car system.
    """
    print("SELF-DRIVING CAR DECISION SYSTEM")

    print("\nTraffic Light Options: Red, Yellow, Green")
    light_status = input("Enter traffic light status: ")

    print("\nObstacle Options: Car, Pedestrian, Cyclist, None")
    obstacle_type = input("Enter obstacle type: ")

    print("\nCrosswalk Present? (Yes/No)")
    crosswalk_present = input("Is there a pedestrian crosswalk? ")

    print(f"\nPROCESSING...")

    print(f"\nFINAL VEHICLE ACTION: {decision_tree(light_status, obstacle_type, crosswalk_present)}")

run_car_decision_system()