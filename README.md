# 🏏 Cricket Match Simulation App

A lightweight Android app that simulates a fun and fast-paced cricket match between two selected teams. Tap the **"Play Ball"** button to simulate each delivery and watch the scoreboard update in real-time with random outcomes. After both innings, the winner is announced!

---

## 📱 App Screens

### 1. **Team Selection Screen**
- Displays a list of all teams from a bundled JSON file.
- Users select **two teams** to play a match.

### 2. **Match Center Screen**
- Simulates a **2-over match (12 balls per team)**.
- Shows:
  - Batting and bowling team names.
  - Real-time scores and overs.
  - Last ball outcome (e.g., 4, 6, W, 1).
  - Match result at the end ("India Wins", etc.).
- Single **"Play Ball"** button at the bottom to simulate each ball.

---

## 🔧 Features

- **Manual Navigation:**  
  Basic screen transitions using Android navigation (no Jetpack Navigation Component used).

- **Randomized Outcomes:**  
  Each tap generates a realistic cricket outcome like runs or a wicket.

- **Simple State Management:**  
  Using ViewModel and LiveData to handle match logic and UI updates.

- **Innings Management:**  
  Automatically switches teams after 12 balls and announces the final result at the end.

- **Minimal UI Design:**  
  Clean and user-friendly UI using XML layouts (`RelativeLayout`, `LinearLayout`).

---

## 🛠️ Tech Stack

- **Kotlin**
- **Android SDK (no Jetpack libraries)**
- **ViewModel & LiveData**
- **Dagger Hilt** (for dependency injection)
- **Manual XML Layouts**

---

## 🎮 How to Play

1. Select two teams from the list.
2. Press "Start Match" to enter the match screen.
3. Tap "Play Ball" to simulate each delivery.
4. Watch the score, overs, and outcome update.
5. After both innings, view the winner!

---

## 🚀 Setup Instructions

1. Clone the repo:
   ```bash
   git clone https://github.com/sai-surya-velaga/WorldT2-App.git
