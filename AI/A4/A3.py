# Medical Diagnosis Using Rule-Based System
# Problem: Assist doctors in diagnosing diseases based on symptoms.
# AI Concept Used: Expert System
# Explanation: The system uses IF-THEN rules to analyze symptoms and suggest
# possible diagnoses.
# Outcome: Improves decision-making accuracy and reduces diagnosis time.

def diagnose(symptom1, symptom2, symptom3):
    """
    Checks symptoms using IF-THEN rules and returns possible disease diagnosis
    """
    # Convert all symptoms to lowercase for comparison
    s1 = symptom1.lower()
    s2 = symptom2.lower()
    s3 = symptom3.lower()

    # Check for empty symptoms
    s = list(filter(None, [s1, s2, s3]))

    if len(s) < 2:
        return "Insufficient symptoms for diagnosis. Please provide at least 2 symptoms."

    # Rule 1: Flu
    if ("fever" in s and "cough" in s) or ("fever" in s and "tiredness" in s) or ("cough" in s and "tiredness" in s):
        return "FLU - Common symptoms include fever, cough, and tiredness."

    # Rule 2: Measles
    if "fever" in s and "rash" in s:
        return "MEASLES - Characterized by fever and skin rash."

    # Rule 3: Migraine
    if "headache" in s and "nausea" in s:
        return "MIGRAINE - Often includes headache and nausea."

    # Rule 4: Common Cold
    if "cough" in s and "sneezing" in s:
        return "COMMON COLD - Includes cough and sneezing."

    # Rule 5: Heart Disease
    if "chest pain" in s and "shortness of breath" in s:
        return "HEART DISEASE - Please seek immediate medical attention!"

    # Rule 6: Allergies
    if "sneezing" in s and "rash" in s:
        return "ALLERGIES - May include sneezing and skin reactions."

    # Rule 7: Strep Throat
    if "fever" in s and "sore throat" in s:
        return "STREP THROAT - Accompanied by fever and sore throat."

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

    display_symptom_guide()
    print("\nPlease enter your symptoms:\n- You can enter up to 3 symptoms\n- Press Enter without typing to skip\n")
    symptom1 = input("Symptom 1: ")
    symptom2 = input("Symptom 2: ")
    symptom3 = input("Symptom 3: ")

    print("\nANALYZING SYMPTOMS...")

    print(f"\nPossible diagnosis: {diagnose(symptom1, symptom2, symptom3)}")

run_diagnosis_system()