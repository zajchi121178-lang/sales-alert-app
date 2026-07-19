import socket
import threading

HOST = '127.0.0.1'
PORT = 9092

def handle_client(conn, addr):
    try:
        data = conn.recv(4096)
        if not data:
            return
        response = b'\x00\x00\x00\x00'
        conn.sendall(response)
    except Exception:
        pass
    finally:
        conn.close()

def start_fake_kafka():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind((HOST, PORT))
    server.listen()
    print(f"[FAKE KAFKA] Сервер запущен на {HOST}:{PORT}")
    print("[FAKE KAFKA] Теперь можешь запускать create_topic.py")
    while True:
        conn, addr = server.accept()
        thread = threading.Thread(target=handle_client, args=(conn, addr))
        thread.start()

if __name__ == "__main__":
    start_fake_kafka()
