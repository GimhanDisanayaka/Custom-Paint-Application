# Custom Computer Graphics Paint Application 🖌️

A lightweight, interactive Paint Application built from scratch using *Java Swing and AWT. This project was developed as a practical implementation of fundamental **Computer Graphics (CG)* concepts during my University 2nd Year, 2nd Semester.

Instead of relying on Java's built-in high-level shape drawing functions (like g.drawLine or g.drawOval), this application manipulates pixels directly at the coordinate level using core CG algorithms.

---

## 🚀 Key Features & Core Implementation

* *Free-Hand Line Drawing:* Uses a custom *DDA (Digital Differential Analyzer) Algorithm* to calculate intermediate pixel positions smoothly as the mouse drags.
* *Circle Generation:* Implemented via the *Midpoint Circle Algorithm, utilizing **8-way symmetry* to minimize calculations and entirely avoid heavy trigonometric ($\sin$, $\cos$) operations.
* *Square/Rectangle Drawing:* Custom mathematical logic utilizing sequential DDA line rendering based on mouse release coordinates.
* *Real-Time Live Preview:* Displays a dynamic, non-destructive outline (bounding box/oval) while dragging the mouse before committing final pixels to the canvas buffer.
* *Interactive UI Overlay:* Includes a custom HUD showing real-time mouse coordinates ($X, Y$), current active tool status, and operational guides.

---

## 🛠️ Tech Stack & Concepts Used

* *Language:* Java 17+
* *GUI Framework:* Java Swing & AWT (Abstract Window Toolkit)
* *Buffer Management:* BufferedImage (for pixel-level color injection via setRGB)
* *Algorithms:* * Digital Differential Analyzer (DDA) Line Algorithm
  * Midpoint Circle Algorithm

---

## 🏃‍♂️ How to Run Locally

### Prerequisites
Make sure you have Java Development Kit (JDK) installed on your system.

### Steps
1. Clone the repository:
   bash
   git clone https://github.com/GimhanDissanayaka/Custom-Paint-Application.git


 2. Navigate to the project directory:
   bash
   cd Custom-Paint-Application
   
   
 3. Compile the Java file:
   bash
   javac AdvancedDDAPaint.java
   
   
 4. Run the application:
   bash
   java AdvancedDDAPaint
   
   
## 🗺️ Future Roadmap (Enhancements)
 * [ ] Implement *Bresenham’s Line Algorithm* to transition completely into integer-only arithmetic (eliminating floating-point overhead).
 * [ ] Add a *Flood Fill (Color Bucket) Tool* using a custom recursive/stack-based boundary fill algorithm.
 * [ ] Implement a *Color Picker Palette* and Stroke Width controller.
 * [ ] Add Undo/Redo functionality using the Command Design Pattern.
## 📄 License
This project is open-source and available under the MIT License.


---
