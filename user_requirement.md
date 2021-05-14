# Task
1. Create REST API in AdminExamController, QuestionController, and UserExamController
2. Fill in the missing field in Answer
3. Fill in the missing field in OneAnswerResponseDTO (Admin and User)

## Database 

```
  -------------
  | User      |
  -------------
  | id        |
  | ...       |
  | exams     |
  -------------
        |
  -------------
  | Exam      |
  -------------
  | id        |
  | name      |
  | user      |
  | questions |
  -------------
        |
  -------------
  | Question  |
  -------------
  | id        |
  | statement |
  | exam      |
  | answers   |
  -------------
        |
  --------------------------
  | Answer                 |
  --------------------------
  | id                     |
  | statement (string)     |
  | question (Question)    |
  | isSeleted (bool)       |
  | isCorrectAnswer (bool) |
  --------------------------
```

## User API

### GET /user/exams
List all the exam id belongs to current user's

Example
```json
{
  "examIds": [1, 2, 3, 4]
}
```

### GET /user/exams/{id}
Query an exam by id, and return all its questions and answer

Response Example
```json
{
  "id": 1,
  "name": "Lorem Ipsum",
  "questions": [
    {
      "id": 1,
      "statement": "Question - Lorem Ipsum",
      "answers": [
        {
          "id": 1,
          "statement": "Answer 1",
          "selected": true
        },{
          "id": 2,
          "statement": "Answer 1",
          "selected": false
        },{
          "id": 3,
          "statement": "Answer 1",
          "selected": false
        }
      ]
    },{
      // ...More question
    }
  ]
}
```

### PUT /user/questions/{questionId}/answers/{answerId}
Select an answer belongs to question of questionId. Will unset all other answer of the same question

Return an empty response on success

## Admin API

### GET /admin/exams
List all the exams in the database

```json
{
  "examIds": [1, 2, 3, 4]
}
```

### GET /admin/exams/{id}

Query an exam by id, and return all the details, including the score, and which answer is correct

1 Score per correctly answered question.

```json
{
  "id": 1,
  "name": "Lorem Ipsum",
  "score": 1,
  "questions": [
    {
      "id": 1,
      "statement": "Question - Lorem Ipsum",
      "answers": [
        {
          "id": 1,
          "statement": "Answer 1",
          "selected": true,
          "correctAnswer": true
        },{
          "id": 2,
          "statement": "Answer 1",
          "selected": false,
          "correctAnswer": false
        },{
          "id": 3,
          "statement": "Answer 1",
          "selected": false,
          "correctAnswer": false
        }
      ]
    },{
      // ...More question
    }
  ]
}
```

