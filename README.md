# Rule-Engine-with-AST
Overview
This is a simple 3-tier rule engine application that evaluates user eligibility based on various attributes like age, department, income, and experience. The system utilizes an Abstract Syntax Tree (AST) to represent conditional rules, allowing for dynamic creation, combination, and modification of rules.

Features
Rule Creation: Define rules using logical operators (AND, OR) and conditions.
Rule Combination: Combine multiple rules into a single AST for efficient evaluation.
Rule Evaluation: Evaluate rules against user attributes to determine eligibility.
Dynamic Rule Management: Modify or create new rules on the fly.
Architecture
Presentation Layer (UI): Simple RESTful API for interacting with the rule engine.
Business Logic Layer (API): Contains services for rule creation, combination, and evaluation.
Data Layer (Database): Stores the rules and associated metadata.

Data Storage
Database Choice
For this application, we can use an in-memory database like H2 or a persistent database like PostgreSQL, depending on the requirements.

Sample Data
id	rule_string	created_at
1	((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)	2024-10-23 10:00:00
2	((age > 30 AND department = 'Marketing')) AND (salary > 20000 OR experience > 5)	2024-10-23 10:05:00

API Design
Endpoints
Create Rule

Endpoint: POST /ui/rules/create
Request Body: A string representing a rule (e.g., "age > 30 AND department = 'Sales'").
Response: Returns a Node object representing the corresponding AST.
Evaluate Rule

Endpoint: POST /ui/rules/evaluate

ombine Rules

Endpoint: POST /ui/rules/combine
Request Body: Two Node objects representing the rules to combine.
Response: Returns a Node object representing the combined AST.
Test Cases
1. Create Individual Rules
Use the create_rule endpoint to create individual rules and verify their AST representation.

2. Combine Rules
Use the combine_rules endpoint to combine multiple rules and ensure the resulting AST reflects the combined logic.

3. Evaluate Rules
Implement sample JSON data and test the evaluate_rule endpoint for different conditions.


Getting Started
Clone the repository:
git clone <repository-url>

Navigate to the project directory:
cd rule-engine-with-ast


Build the application using Maven:
mvn clean install

Run the application:
mvn spring-boot:run


