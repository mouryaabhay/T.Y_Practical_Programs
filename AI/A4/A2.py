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
    light_status = light_status.lower()

    if light_status == "red":
        return "Stop"
    elif light_status == "yellow":
        return "Slow Down"
    elif light_status == "green":
        return "Go"
    else:
        return "Invalid Traffic Light"

def check_obstacle(obstacle_type):
    """
    Identifies obstacle type and decides action
    """
    obstacle_type = obstacle_type.lower()

    if obstacle_type == "pedestrian":
        return "Stop"
    elif obstacle_type == "car":
        return "Avoid"
    elif obstacle_type == "cyclist":
        return "Slow Down"
    elif obstacle_type == "none":
        return "No Action"
    else:
        return "Unknown Obstacle"

def decision_tree(light_status, obstacle_type, crosswalk_present):
    """
    Combines road condition and obstacle data to make final driving decision
    """
    light_decision = check_traffic_rule(light_status)
    obstacle_decision = check_obstacle(obstacle_type)

    # Decision tree logic
    if light_decision == "Stop":
        return "STOP - Red light detected"

    if light_decision == "Invalid Traffic Light":
        return "ERROR - Unable to read traffic light, stopping as precaution"

    if obstacle_decision == "Stop":
        if obstacle_type.lower() == "pedestrian":
            return "STOP IMMEDIATELY - Pedestrian detected"
        else:
            return "STOP - Obstacle requires stopping"

    if obstacle_decision == "Avoid":
        return "AVOID - Maneuver around the car"

    if obstacle_decision == "Slow Down":
        if crosswalk_present.lower() == "yes":
            return "SLOW DOWN - Cyclist near crosswalk, proceed with caution"
        else:
            return "SLOW DOWN - Cyclist detected"

    if light_decision == "Go" and obstacle_decision == "No Action":
        return "MOVE - Clear path, proceed normally"

    if light_decision == "Slow Down" and obstacle_decision == "No Action":
        return "SLOW DOWN - Yellow light, prepare to stop"

    if light_decision == "Go" and obstacle_decision == "Unknown Obstacle":
        return "CAUTION - Unknown object detected, slow down"

    return "CAUTION - Proceed with care"

def run_car_decision_system():
    """
    Runs the self-driving car system.
    """
    print("SELF-DRIVING CAR DECISION SYSTEM")

    while True:
        print("\nTraffic Light Options: Red, Yellow, Green")
        light_status = input("Enter traffic light status: ")

        print("\nObstacle Options: Car, Pedestrian, Cyclist, None")
        obstacle_type = input("Enter obstacle type: ")

        print("\nCrosswalk Present? (Yes/No)")
        crosswalk_present = input("Is there a pedestrian crosswalk? ")

        print(f"\nPROCESSING...")

        final_decision = decision_tree(light_status, obstacle_type, crosswalk_present)

        print(f"\nFINAL VEHICLE ACTION: {final_decision}")

        another = input("\nWould you like to test another scenario? (yes/no): ")
        if another.lower() != "yes":
            break

    print("\nProgram terminated")

run_car_decision_system()