# Реализация логики ДЗ: алерт, если сумма (quantity * price) за последнюю минуту > 3000
# Данные моковые, но логика расчёта (amount = qty * price) и агрегации — точная

ALERT_THRESHOLD = 3000.0
WINDOW_MINUTES = 1

# Моковые данные: каждый dict — это одно событие покупки
# В реальном пайплайне они приходят из Kafka (через Datagen)
mock_events = [
    {"product_id": 1, "quantity": 10, "price": 250.0},   # сумма = 2500
    {"product_id": 2, "quantity": 5,  "price": 300.0},   # сумма = 1500
    {"product_id": 1, "quantity": 8,  "price": 400.0},   # сумма = 3200 (это вызовет алерт!)
    {"product_id": 3, "quantity": 20, "price": 100.0},   # сумма = 2000
]

print(f"[SALES ALERT DEMO] Окно агрегации: {WINDOW_MINUTES} мин, порог: {ALERT_THRESHOLD}")
print("-" * 50)

# 1. Считаем amount для каждого события (как требует задание: quantity * price)
for i, event in enumerate(mock_events):
    event["amount"] = event["quantity"] * event["price"]
    print(f"Event #{i+1}: product_id={event['product_id']}, qty={event['quantity']}, price={event['price']} -> amount={event['amount']:.2f}")

# 2. Имитируем оконную агрегацию за "последнюю минуту"
# В реальном Spark Structured Streaming это было бы: .withWatermark(...).groupBy(window(...))
# Здесь мы просто суммируем все события как "одну минуту" (для демонстрации)
total_minute_amount = sum(e["amount"] for e in mock_events)

print("-" * 50)
print(f"[AGGREGATION] Общая сумма за окно ({WINDOW_MINUTES} мин): {total_minute_amount:.2f}")

# 3. Проверяем условие алерта
if total_minute_amount > ALERT_THRESHOLD:
    print("[ALERT] ТРЕВОГА! Сумма за последнюю минуту превысила 3000!")
else:
    print("[OK] Сумма в пределах нормы.")

print("=" * 50)
