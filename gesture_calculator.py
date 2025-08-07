import cv2
import mediapipe as mp
import time

# Initialize MediaPipe
mp_hands = mp.solutions.hands
hands = mp_hands.Hands(max_num_hands=2)
mp_draw = mp.solutions.drawing_utils

cap = cv2.VideoCapture(0)

expression = ""
last_selected = None
select_time = 1

buttons = [
    ("7", 50, 100), ("8", 150, 100), ("9", 250, 100), ("/", 350, 100),
    ("4", 50, 200), ("5", 150, 200), ("6", 250, 200), ("*", 350, 200),
    ("1", 50, 300), ("2", 150, 300), ("3", 250, 300), ("-", 350, 300),
    ("0", 50, 400), ("C", 150, 400), ("=", 250, 400), ("+", 350, 400),
]

def draw_buttons(img):
    for (text, x, y) in buttons:
        cv2.rectangle(img, (x, y), (x+80, y+80), (0,0,0), -1)
        cv2.putText(img, text, (x+25, y+55), cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 255, 255), 2)

while True:
    success, img = cap.read()
    img = cv2.flip(img, 1) 
    img_rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    results = hands.process(img_rgb)

    draw_buttons(img)

    if results.multi_hand_landmarks:
        hand_landmarks = results.multi_hand_landmarks[0]
        lm_list = []
        for id, lm in enumerate(hand_landmarks.landmark):
            h, w, _ = img.shape
            lm_list.append((int(lm.x * w), int(lm.y * h)))

        index_finger_tip = lm_list[8] 
        cv2.circle(img, index_finger_tip, 10, (0, 255, 0), cv2.FILLED)

        # Check which button is being pointed at
        mp_draw.draw_landmarks(img, hand_landmarks, mp_hands.HAND_CONNECTIONS)
        for (text, x, y) in buttons:
            if x < index_finger_tip[0] < x+80 and y < index_finger_tip[1] < y+80:
                cv2.rectangle(img, (x, y), (x+80, y+80), (0, 255, 0), 3)

                if last_selected == text:
                    if time.time() - select_time > 1:
                        if text == "C":
                            expression = ""
                        elif text == "=":
                            try:
                                expression = str(eval(expression))
                            except:
                                expression = "Error"
                        else:
                            expression += text
                        last_selected = None
                        select_time = 0
                else:
                    last_selected = text
                    select_time = time.time()
                break

    cv2.rectangle(img, (50, 30), (490, 80), (0, 0, 0), -1)
    cv2.putText(img, expression, (60, 70), cv2.FONT_HERSHEY_SIMPLEX, 1.5, (255, 255, 255), 2)

    cv2.imshow("One-Finger Gesture Calculator", img)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
