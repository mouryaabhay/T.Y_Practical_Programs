class BookTicket {
    private int availableTickets = 100;

    public synchronized void ticketBooking(int ticketsRequested) {
        if (ticketsRequested <= availableTickets) {
            System.out.println("Successfully booked " + ticketsRequested + " ticket(s).");
            availableTickets -= ticketsRequested;
            System.out.println("Remaining tickets: " + availableTickets);
        } else {
            System.out.println("Failed to book " + ticketsRequested + " ticket(s). " + availableTickets + " available.");
        }
    }
}

class TicketApp extends Thread {
    private BookTicket bt;
    private int ticketsRequested;

    TicketApp(BookTicket bt, int ticketsRequested) {
        this.bt = bt;
        this.ticketsRequested = ticketsRequested;
    }

    public void run() {
        bt.ticketBooking(ticketsRequested);
    }
}

public class B4 {
    public static void main(String[] args) throws InterruptedException {
        BookTicket b = new BookTicket();

        TicketApp t1 = new TicketApp(b, 50);
        TicketApp t2 = new TicketApp(b, 50);
        TicketApp t3 = new TicketApp(b, 10);

        t1.start();
        t2.start();
        t3.start();
    }
}