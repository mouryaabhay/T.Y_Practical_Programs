# To build a Simple Chatbot
# Problem: Automate responses to student FAQs.
# AI Concept Used: Natural Language Processing
# Explanation: The chatbot understands user input and generates appropriate responses.
# Outcome: Reduces manual support and improves response time.

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

def run_chatbot():
    """Runs the Student FAQ Chatbot in a concise loop."""
    print("Student FAQ Chatbot!")
    print("You can ask me about: admission, fees, exam schedule, timetable.")
    print("Type 'exit' to end the conversation.")

    while True:
        user_input = input("You: ")
        if not user_input:
            print("Chatbot: Please type a question.")
            continue
        if user_input.lower() == "exit":
            print("\nProgram terminated")
            break
        print(f"Chatbot: {chatbot_response(user_input)}")

run_chatbot()