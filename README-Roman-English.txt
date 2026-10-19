PythonSikho - Starter Android Project
Version: 0.1.0

WHAT IS INCLUDED
- Android app wrapper
- Offline sample lessons in Roman English
- Speaker buttons using Android Text-to-Speech
- Voice settings shortcut
- Starter home screen
- GitHub Actions workflow to build a debug APK

IMPORTANT LIMITATION
This is a starter project, not a finished Play Store app.
The Practical IDE button is only a placeholder. A real offline Python interpreter has NOT been integrated yet.
Roman Urdu written in Latin letters may not be pronounced perfectly by a standard English TTS voice.
A suitable voice may need to be installed in Android's Text-to-Speech settings.

BUILDING
The project uses Android Gradle Plugin 8.7.3 and requires JDK 17 plus Android SDK platform 35.
For cloud build, upload this project to a GitHub repository and run the workflow:
Actions > Build PythonSikho APK > Run workflow.
Download the generated artifact named PythonSikho-debug-apk.
This produces a debug APK for testing, not a Play Store release bundle.

NEXT DEVELOPMENT PHASES
1. Test on actual Android phone and fix any build/device issues.
2. Add real embedded Python runtime and code editor.
3. Add lesson data structure, quizzes, bookmarks, progress saving.
4. Create signed release build / AAB and meet current Play Console requirements.

APP ID
com.pythonsikho.app

NO INTERNET PERMISSION
The starter app intentionally does not request internet permission. Its lessons are bundled locally.
