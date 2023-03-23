import os

path = "./scripts"

for sub_path in os.listdir(path):
    full_sub_path = os.path.join(path, sub_path)
    for filename in os.listdir(full_sub_path):
        if filename.endswith(".py") :
            if os.name == 'nt': # if Windows
                command = "pyinstaller --onefile " + os.path.join(full_sub_path, filename)
            elif os.name == 'posix': # if macOS
                command = "py2applet --make-setup " + os.path.join(full_sub_path, filename) + " && python setup.py py2app -A"

            # execute the command
            os.system(command)

        elif filename.endswith(".java") :
            command = "javac " + os.path.join(full_sub_path, filename) + ""

            # execute the first command
            os.system(command)

            # create JAR file
            class_name = os.path.splitext(filename)[0]  # extract class name from filename
            jar_file = os.path.join(full_sub_path, class_name + ".jar")
            command = "jar cvf " + jar_file + " " + os.path.join(full_sub_path, class_name + ".class")

            # execute the final command
            os.system(command)
