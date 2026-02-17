# Self-Driving Car Knowledge Base System
# This program simulates decision-making for a self-driving car

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

    print(f"\n--- Processing Decision ---")
    print(f"Traffic Light: {light_status.upper()} -> Initial decision: {light_decision}")
    print(f"Obstacle: {obstacle_type.capitalize()} -> Initial decision: {obstacle_decision}")
    print(f"Crosswalk Present: {crosswalk_present}")

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

def main():
    """
    Main function to run the self-driving car system
    """
    print("=" * 60)
    print("SELF-DRIVING CAR DECISION SYSTEM")
    print("=" * 60)
    print("This system simulates how a self-driving car makes decisions")
    print("based on traffic lights, obstacles, and road conditions.")
    print("-" * 60)

    # Get traffic light status
    print("\nTraffic Light Options: Red, Yellow, Green")
    light_status = input("Enter traffic light status: ")

    # Get obstacle type
    print("\nObstacle Options: Car, Pedestrian, Cyclist, None")
    obstacle_type = input("Enter obstacle type: ")

    # Get crosswalk information
    print("\nCrosswalk Present? (Yes/No)")
    crosswalk_present = input("Is there a pedestrian crosswalk? ")

    # Make final decision
    final_decision = decision_tree(light_status, obstacle_type, crosswalk_present)

    # Display result
    print("\n" + "=" * 60)
    print("FINAL VEHICLE ACTION:")
    print(f">>> {final_decision} <<<")
    print("=" * 60)

# Run the self-driving car system
if __name__ == "__main__":
    main()

    # Option to run multiple scenarios
    print("\n" + "=" * 60)
    another = input("Would you like to test another scenario? (yes/no): ")

    while another.lower() == "yes":
        print("\n" + "=" * 60)
        light_status = input("Enter traffic light status (Red/Yellow/Green): ")
        obstacle_type = input("Enter obstacle type (Car/Pedestrian/Cyclist/None): ")
        crosswalk_present = input("Is there a pedestrian crosswalk? (Yes/No): ")

        final_decision = decision_tree(light_status, obstacle_type, crosswalk_present)

        print("\n" + "=" * 60)
        print("FINAL VEHICLE ACTION:")
        print(f">>> {final_decision} <<<")
        print("=" * 60)

        another = input("\nWould you like to test another scenario? (yes/no): ")

    print("\nSelf-driving car system terminated.")