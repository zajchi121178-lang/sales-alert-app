# Моковый producer — имитирует отправку сообщений для ДЗ
topic = "purchases"

records = [
    {"user_id": 101, "amount": 150.0},
    {"user_id": 102, "amount": 89.5},
    {"user_id": 103, "amount": 320.75},
]

for i, record in enumerate(records):
    print(f"[MOCK PRODUCER] Отправлено сообщение #{i+1} в тему '{topic}': user_id={record['user_id']}, amount={record['amount']}")

print("[MOCK PRODUCER] Имитация отправки завершена.")
