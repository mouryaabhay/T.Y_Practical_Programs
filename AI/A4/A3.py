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
    if ("fever" in symptoms and "cough" in symptoms) or \
       ("fever" in symptoms and "tiredness" in symptoms) or \
       ("cough" in symptoms and "tiredness" in symptoms):
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
    print("(You can also enter other symptoms)")

def main():
    """
    Main function to run the medical diagnosis system
    """
    print("=" * 60)
    print("MEDICAL DIAGNOSIS ASSISTANT SYSTEM")
    print("=" * 60)
    print("This system provides possible diagnoses based on symptoms.")
    print("NOTE: This is for educational purposes only.")
    print("Always consult a healthcare professional for actual diagnosis.")
    print("-" * 60)

    # Display symptom guide
    display_symptom_guide()
    print("-" * 60)

    # Accept symptoms from user
    print("\nPlease enter your symptoms (you can enter up to 3 symptoms):")
    symptom1 = input("Enter symptom 1 (or press Enter to skip): ")
    symptom2 = input("Enter symptom 2 (or press Enter to skip): ")
    symptom3 = input("Enter symptom 3 (or press Enter to skip): ")

    print("\n" + "=" * 60)
    print("ANALYZING SYMPTOMS...")
    print("=" * 60)

    # Get diagnosis
    result = diagnose(symptom1, symptom2, symptom3)

    # Display diagnosis
    print("\nDIAGNOSIS RESULT:")
    print(f">>> {result}")
    print("=" * 60)

    # Additional recommendations
    print("\nRECOMMENDATIONS:")
    print("1. This is an AI-based preliminary analysis only")
    print("2. Consult a doctor for proper medical advice")
    print("3. If symptoms are severe, seek immediate medical attention")
    print("4. Follow prescribed treatments and medications")
    print("=" * 60)

# Run the medical diagnosis system
if __name__ == "__main__":
    main()

    # Option for multiple diagnoses
    print("\n" + "=" * 60)
    another = input("Would you like to diagnose another set of symptoms? (yes/no): ")

    while another.lower() == "yes":
        print("\n" + "=" * 60)
        display_symptom_guide()
        print("-" * 60)

        symptom1 = input("Enter symptom 1 (or press Enter to skip): ")
        symptom2 = input("Enter symptom 2 (or press Enter to skip): ")
        symptom3 = input("Enter symptom 3 (or press Enter to skip): ")

        print("\n" + "=" * 60)
        print("ANALYZING SYMPTOMS...")
        print("=" * 60)

        result = diagnose(symptom1, symptom2, symptom3)

        print("\nDIAGNOSIS RESULT:")
        print(f">>> {result}")
        print("=" * 60)

        another = input("\nWould you like to diagnose another set of symptoms? (yes/no): ")

    print("\nMedical diagnosis system terminated. Stay healthy!")