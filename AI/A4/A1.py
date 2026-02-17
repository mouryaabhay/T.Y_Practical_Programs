# Simple Chatbot for Student FAQs
# This program answers student questions about admission, fees, exams, and timetable

def chatbot_response(user_input):
    """
    Processes the student's question, matches keywords, and returns appropriate response
    """
    # Convert to lowercase for easier matching
    user_input = user_input.lower()

    # Check for keywords and return appropriate responses
    if "admission" in user_input:
        return "Admission details: Applications are open from March to June. Please visit our website to apply online."
    elif "fees" in user_input:
        return "Fee structure: Tuition fee is $5000 per semester. Additional fees may apply for labs and activities."
    elif "exam" in user_input:
        return "Exam schedule: Mid-term exams start from October 15. Final exams begin December 10."
    elif "timetable" in user_input:
        return "Class timetable: Regular classes run from 9 AM to 4 PM, Monday through Friday."
    elif "hello" in user_input or "hi" in user_input:
        return "Hello! How can I help you today? You can ask about admission, fees, exams, or timetable."
    elif "help" in user_input:
        return "I can answer questions about: admission, fees, exams, and timetable. Type 'exit' to quit."
    else:
        return "I'm sorry, I don't have information about that. Please ask about admission, fees, exams, or timetable."

def main():
    """
    Main function to run the chatbot
    """
    print("=" * 50)
    print("Welcome to the Student FAQ Chatbot!")
    print("=" * 50)
    print("You can ask me about:")
    print("- Admission")
    print("- Fees")
    print("- Exam schedule")
    print("- Timetable")
    print("Type 'exit' to end the conversation.")
    print("-" * 50)

    # Keep the chatbot running until user types 'exit'
    while True:
        # Accept user input
        user_input = input("You: ")

        # Check if input is empty
        if len(user_input) == 0:
            print("Chatbot: Please type a question.")
            continue

        # Check if user wants to exit
        if user_input.lower() == "exit":
            print("Chatbot: Thank you for using the FAQ Chatbot. Goodbye!")
            break

        # Get and display chatbot response
        response = chatbot_response(user_input)
        print(f"Chatbot: {response}")
        print("-" * 50)

# Run the chatbot
if __name__ == "__main__":
    main()