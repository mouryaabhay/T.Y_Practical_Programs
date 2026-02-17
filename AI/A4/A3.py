# Medical Diagnosis System
# This program assists in diagnosing diseases based on symptoms

def diagnose(symptom1, symptom2, symptom3):
    """
    Checks symptoms using IF-THEN rules and returns possible disease diagnosis
    """
    # Convert all symptoms to lowercase for comparison
    s1 = symptom1.lower()
    s2 = symptom2.lower()
    s3 = symptom3.lower()

    # Check for empty symptoms
    symptoms = [s for s in [s1, s2, s3] if s != ""]

    if len(symptoms) < 2:
        return "Insufficient symptoms for diagnosis. Please provide at least 2 symptoms."

    # Rule 1: Flu
    if ("fever" in symptoms and "cough" in symptoms) or ("fever" in symptoms and "tiredness" in symptoms) or ("cough" in symptoms and "tiredness" in symptoms):
        return "Possible diagnosis: FLU - Common symptoms include fever, cough, and tiredness."

    # Rule 2: Measles
    if "fever" in symptoms and "rash" in symptoms:
        return "Possible diagnosis: MEASLES - Characterized by fever and skin rash."

    # Rule 3: Migraine
    if "headache" in symptoms and "nausea" in symptoms:
        return "Possible diagnosis: MIGRAINE - Often includes headache and nausea."

    # Rule 4: Common Cold
    if "cough" in symptoms and "sneezing" in symptoms:
        return "Possible diagnosis: COMMON COLD - Includes cough and sneezing."

    # Rule 5: Heart Disease
    if "chest pain" in symptoms and "shortness of breath" in symptoms:
        return "Possible diagnosis: HEART DISEASE - Please seek immediate medical attention!"

    # Rule 6: Allergies
    if "sneezing" in symptoms and "rash" in symptoms:
        return "Possible diagnosis: ALLERGIES - May include sneezing and skin reactions."

    # Rule 7: COVID-19
    if ("fever" in symptoms and "cough" in symptoms and "tiredness" in symptoms):
        return "Possible diagnosis: COVID-19 - Please get tested and isolate."

    # Rule 8: Strep Throat
    if "fever" in symptoms and "sore throat" in symptoms:
        return "Possible diagnosis: STREP THROAT - Accompanied by fever and sore throat."

    # If no specific rule matches
    return "Unable to determine specific condition. Please consult a doctor for proper diagnosis."

def display_symptom_guide():
    """
    Displays available symptoms that users can enter
    """
    print("\nCommon symptoms you can enter:")
    print("- fever")
    print("- cough")
    print("- tiredness")
    print("- rash")
    print("- headache")
    print("- nausea")
    print("- sneezing")
    print("- chest pain")
    print("- shortness of breath")
    print("- sore throat")

def run_diagnosis_system():
    """
    Runs the medical diagnosis system.
    """
    print("MEDICAL DIAGNOSIS ASSISTANT SYSTEM")

    while True:
        display_symptom_guide()
        print("\nPlease enter your symptoms:\n- You can enter up to 3 symptoms\n- Press Enter without typing to skip\n")
        symptom1 = input("Symptom 1: ")
        symptom2 = input("Symptom 2: ")
        symptom3 = input("Symptom 3: ")

        print("\nANALYZING SYMPTOMS...")

        result = diagnose(symptom1, symptom2, symptom3)

        print(f"\nDIAGNOSIS RESULT: {result}")

        another = input("\nWould you like to diagnose again? (yes/no): ")
        if another.lower() != "yes":
            break

    print("\nProgram terminated")

run_diagnosis_system()