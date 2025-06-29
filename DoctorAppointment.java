import java.util.*;

class Doctor {
    String name;
    String specialization;
    String[] availableSlots;

    public Doctor(String name, String specialization, String[] slots) {
        this.name = name;
        this.specialization = specialization;
        this.availableSlots = slots;
    }

    public boolean bookSlot(int slotIndex) {
        if (slotIndex >= 0 && slotIndex < availableSlots.length) {
            if (!availableSlots[slotIndex].equals("Booked")) {
                availableSlots[slotIndex] = "Booked";
                return true;
            }
        }
        return false;
    }

    public void displayAvailableSlotsWithIndex() {
        System.out.println("\nAvailable slots for Dr. " + name + " (" + specialization + "):");
        for (int i = 0; i < availableSlots.length; i++) {
            if (!availableSlots[i].equals("Booked")) {
                System.out.println("[" + i + "] " + availableSlots[i]);
            }
        }
    }

    public boolean hasAvailableSlots() {
        for (String slot : availableSlots) {
            if (!slot.equals("Booked")) {
                return true;
            }
        }
        return false;
    }
}

class Patient {
    String name;
    String contact;
    String reason;

    public Patient(String name, String contact, String reason) {
        this.name = name;
        this.contact = contact;
        this.reason = reason;
    }
}

public class DoctorAppointment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Swathi", "Cardiologist", new String[]{"10:00am", "11:00am", "12:00am"}));
        doctors.add(new Doctor("Madhan Mohan", "Neurologist", new String[]{"12:00pm", "1:00pm", "2:00pm"}));
        doctors.add(new Doctor("Ravi", "General Physician", new String[]{"2:00pm", "3:00pm", "4:00pm"}));
        doctors.add(new Doctor("Preethi", "Dermatologist", new String[]{"4:00pm", "5:00pm", "6:00pm"}));
        doctors.add(new Doctor("Kiran", "Dentist", new String[]{"6:00pm", "7:00pm","8:00pm"}));
        doctors.add(new Doctor("Anjali", "Ophthalmologist", new String[]{"8:00am", "9:00am", "10:00am"}));
        doctors.add(new Doctor("Rajesh", "Orthopedic", new String[]{"10:00am", "11:00am", "12:00am"}));
        doctors.add(new Doctor("Sneha", "ENT Specialist", new String[]{"12:00pm", "1:00pm", "2:00pm"}));
        doctors.add(new Doctor("Priya", "Gynecologist", new String[]{"2:00pm", "3:00pm", "4:00pm"}));
        doctors.add(new Doctor("Arun", "Pediatrician", new String[]{"4:00pm", "5:00pm", "6:00pm"}));

        
        Map<String, String> illnessMap = new HashMap<>();
        illnessMap.put("fever", "General Physician");
        illnessMap.put("chest pain", "Cardiologist");
        illnessMap.put("headache", "Neurologist");
        illnessMap.put("skin allergy", "Dermatologist");
        illnessMap.put("tooth pain", "Dentist");
        illnessMap.put("eye irritation", "Ophthalmologist");
        illnessMap.put("joint pain", "Orthopedic");
        illnessMap.put("ear pain", "ENT Specialist");
        illnessMap.put("pregnancy checkup", "Gynecologist");
        illnessMap.put("child fever", "Pediatrician");

        // Patient details
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();

        System.out.print("Enter contact number: ");
        String patientContact = scanner.nextLine();

        System.out.print("Enter reason for appointment: ");
        String reason = scanner.nextLine().toLowerCase().trim();

        Patient patient = new Patient(patientName, patientContact, reason);

        String specialization = illnessMap.get(reason);

        if (specialization == null) {
            System.out.println("Sorry, no specialization found for the given reason.");
            scanner.close();
            return;
        }

        Doctor matchedDoctor = null;
        for (Doctor doc : doctors) {
            if (doc.specialization.equalsIgnoreCase(specialization)) {
                matchedDoctor = doc;
                break;
            }
        }

        if (matchedDoctor == null) {
            System.out.println("No doctor available for specialization: " + specialization);
            scanner.close();
            return;
        }

        if (!matchedDoctor.hasAvailableSlots()) {
            System.out.println("Sorry, no slots available for Dr. " + matchedDoctor.name);
            scanner.close();
            return;
        }

        boolean booked = false;

        while (!booked) {
            matchedDoctor.displayAvailableSlotsWithIndex();

            System.out.print("Choose a slot by index: ");
            int slotIndex = scanner.nextInt();
            scanner.nextLine(); 

            if (matchedDoctor.bookSlot(slotIndex)) {
                System.out.println("\nAppointment booked successfully!");
                System.out.println("Patient: " + patient.name);
                System.out.println("Doctor: Dr. " + matchedDoctor.name + " (" + matchedDoctor.specialization + ")");
                System.out.println("Time: " + slotIndex);
                booked = true;
            } else {
                System.out.println("Invalid or unavailable slot index. Please try again.");
            }
        }

        scanner.close();
    }
}
