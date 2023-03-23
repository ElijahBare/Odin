import os

path = "./scripts"

for filename in os.listdir(path):
    if filename.endswith(".py") :
        if os.name == 'nt': # if Windows
            command = "pyinstaller --onefile " + os.path.join(path, filename)
        elif os.name == 'posix': # if macOS
            command = "py2applet --make-setup " + os.path.join(path, filename) + " && python setup.py py2app -A"

        # execute the command
        os.system(command)