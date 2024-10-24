// Add event listeners for buttons
document.getElementById('createRuleBtn').addEventListener('click', createRule);
document.getElementById('combineRulesBtn').addEventListener('click', combineRules);
document.getElementById('evaluateRuleBtn').addEventListener('click', evaluateRule);

// Set the base URL for your backend API
const apiBaseUrl = 'http://localhost:8080/api/rule_engine'; // Make sure this URL is correct

const ERROR_MESSAGES = {
    EMPTY_RULE: 'Please enter a rule.',
    EMPTY_COMBINE_RULES: 'Please enter at least one rule to combine.',
    EMPTY_EVALUATE: 'Please enter both rule and data to evaluate.',
    INVALID_JSON: 'Invalid JSON format for data.'
};

// Function to create a rule
async function createRule() {
    const ruleString = document.getElementById('ruleInput').value;
    
    // Check if the input is not empty
    if (!ruleString.trim()) {
        displayResult({ error: ERROR_MESSAGES.EMPTY_RULE });
        return;
    }
    try {
        const response = await fetch(`${apiBaseUrl}/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ rule: ruleString }), // Wrap the rule string in an object
        });

        if (!response.ok) {
            throw new Error('Failed to create rule. Server responded with status ' + response.status);
        }

        const result = await response.json();
        displayResult(result);
    } catch (error) {
        displayResult({ error: error.message });
    }
}

// Function to combine rules
async function combineRules() {
    const rulesInput = document.getElementById('combineRulesInput').value;
    const rules = rulesInput.split(',').map(rule => rule.trim());

    if (rules.length === 0 || !rules[0]) {
        displayResult({ error: ERROR_MESSAGES.EMPTY_COMBINE_RULES });
        return;
    }

    try {
        const response = await fetch(`${apiBaseUrl}/combine`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ rules })// Send an object containing the rules array
        });

        if (!response.ok) {
            throw new Error('Failed to combine rules. Server responded with status ' + response.status);
        }

        const result = await response.json();
        displayResult(result);
    } catch (error) {
        displayResult({ error: error.message });
    }
}

// Function to evaluate a rule
async function evaluateRule() {
    const rule = document.getElementById('evaluateRuleInput').value;
    const dataInput = document.getElementById('evaluateDataInput').value;

    if (!rule.trim() || !dataInput.trim()) {
        displayResult({ error: ERROR_MESSAGES.EMPTY_EVALUATE });
        return;
    }

    let data;
    try {
        data = JSON.parse(dataInput); // Parse the data input as JSON
    } catch (error) {
        displayResult({ error: ERROR_MESSAGES.INVALID_JSON });
        return;
    }

    try {
        const response = await fetch(`${apiBaseUrl}/evaluate`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ rule, data }), // Send both rule and data as an object
        });

        if (!response.ok) {
            throw new Error('Failed to evaluate rule. Server responded with status ' + response.status);
        }

        const result = await response.json();
        displayResult(result);
    } catch (error) {
        displayResult({ error: error.message });
    }
}

// Function to display results or errors
function displayResult(result) {
    const resultsElement = document.getElementById('results');
    resultsElement.textContent = JSON.stringify(result, null, 2);
}