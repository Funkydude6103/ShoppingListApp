# Shopping List App

A simple and efficient **Shopping List** application that allows users to add, manage, and delete shopping items. The app integrates Firebase Realtime Database for data storage, providing real-time updates.

## 🚀 Objective
The goal of this project is to create a **Shopping List application** where users can add, view, and manage their shopping list items. The app features Firebase Authentication for secure login and registration, and Firebase Realtime Database to store the shopping list data.

## 🌟 Features
- **User Authentication**: 
  - Users can log in or register using email and password.
  - Includes a "Forgot Password" feature for easy password recovery.
- **Shopping List Page**:
  - Displays a list of shopping items with real-time updates.
  - Each item shows:
    - **Item Name**
    - **Quantity**
    - **Price**
  - Users can delete an item, which removes it from the list in real-time.
- **Add New Item**: 
  - Users can add new shopping items using a simple form.
  - Automatically saves the item data to Firebase Realtime Database.
- **Splash Screen**: 
  - A visually engaging splash screen with a **Translate** and **Scale** animation for the app's logo.
- **Responsive UI**:
  - The app is responsive and adapts to different screen sizes and orientations using `ConstraintLayout`.

## 📋 Development Guidelines
- All strings are stored in `strings.xml`.
- All dimensions are defined in `dimens.xml`.
- All colors are declared in `colors.xml`.
- Use `ConstraintLayout` for building responsive UI layouts.
- Consistency in design, colors, fonts, and spacing is essential.

## 💻 Deliverables
- XML layouts for each screen and fragment.
- Java code for handling authentication, data operations (add, delete), and real-time updates.
- Firebase Realtime Database structure for shopping items.
- Splash screen with logo animations.
- Fully functional shopping list page with item addition and deletion features.

## 📱 App Flow
1. **Splash Screen**: Displays the app’s logo with animations:
    - **Translate**: The logo moves from the top of the screen to the center.
    - **Scale**: The logo grows slightly in size as it moves.
2. **Authentication Screen**: 
    - Users log in or register using Firebase Authentication.
    - A "Forgot Password" option is available.
3. **Shopping List Page**:
    - Displays a list of items stored in Firebase Realtime Database.
    - Users can add new items and delete them from the list.
4. **Add Item**:
    - A simple form where users can input the **Item Name**, **Quantity**, and **Price** of a new shopping item.
    - After submission, the item is saved in Firebase Realtime Database.
5. **Responsive Design**:
    - The app is designed to be responsive on both portrait and landscape orientations using `ConstraintLayout`.

## 🛠 Technologies Used
- **Firebase Authentication** for user authentication.
- **Firebase Realtime Database** to store shopping list data.
- **Android SDK** for app development.
- **ConstraintLayout** for building responsive UI.
- **Java** for backend logic and fragment management.

## 🔄 Navigation
- `StartActivityForResult` is used to manage navigation between activities.
- Splash screen transitions to the login screen after animation completes.

## 📂 File Structure
- **`strings.xml`**: Contains all app text.
- **`dimens.xml`**: Defines dimensions used throughout the app.
- **`colors.xml`**: Stores color definitions for consistent theming.
- **`activity_main.xml`**: Layout for the main shopping list page.
- **`activity_login.xml`**: Layout for the authentication page.
- **`dialog_add_item.xml`**: Layout for the dialog used to add new items.

## 🎨 Design Consistency
- The app maintains a unified design across all screens, ensuring clarity and ease of use.
- Buttons, forms, and inputs are clearly labeled and user-friendly.

## 🧪 Testing
- Test the navigation flow to ensure smooth transitions between screens and fragments.
- Validate that the UI remains responsive across different screen sizes and orientations.
- Ensure that adding and deleting items from the shopping list works smoothly with Firebase Realtime Database.

---

## 📸 Demo
<div style="display: flex; justify-content: center; align-items: center;">
    <video class="as" src="https://github.com/user-attachments/assets/ffed774b-bfa5-41d8-b9bf-d5a553a93902" controls="controls" style="max-width: 100%;">
        Your browser does not support the video tag.
    </video>
</div>

---

## 📸 Responsiveness

---

<img src="https://github.com/user-attachments/assets/11aceca7-eb45-4eb5-abfd-ca4dffd3eb09" alt="W3Schools.com">

---

Made with ❤ by Tayyab Anees
