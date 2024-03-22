# Importing the MonkeyDevice class from the com.android.monkeyrunner module.
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

# Connecting to the current device and returning a MonkeyDevice object.
device = MonkeyRunner.waitForConnection()

# Installing the Android package and verifying the installation.
is_installed = device.installPackage('myproject/bin/MyCalculatorApp.apk')

# Defining the package and activity names for the app.
package = 'com.example.android.calculatorapp'
activity = 'com.example.android.calculatorapp.MainActivity'
runComponent = package + '/' + activity  # Defining the component to start.

# Starting the specified component.
device.startActivity(component=runComponent)

# Initializing data structures for tracking coverage.
nodes = set()
edges = set()
edge_pairs = set()
previous_action = None

# Defining a list representing the sequence of user interactions.
# Each interaction is a tuple ('action_type', 'details').
actions = [
    ('press', 'KEYCODE_1'),
    ('press', 'KEYCODE_PLUS'),
    ('press', 'KEYCODE_2'),
    ('press', 'KEYCODE_EQUALS')
]

# Function to execute actions and update coverage data.
def execute_action(action, details):
    global previous_action
    # Executing the action.
    if action == 'press':
        device.press(details, MonkeyDevice.DOWN_AND_UP)
    
    # Updating nodes and edges.
    nodes.add(details)
    if previous_action is not None:
        edge = (previous_action, details)
        edges.add(edge)
        
        # Updating edge-pairs if there's a previous edge.
        if len(edges) > 1:
            last_edge = list(edges)[-2]  # Getting the second last edge added.
            edge_pair = (last_edge, edge)
            edge_pairs.add(edge_pair)
    
    previous_action = details

# Looping through actions to simulate user interaction.
for action, details in actions:
    execute_action(action, details)
    MonkeyRunner.sleep(1)  # Adding delay to simulate real user interaction.

# Calculating coverage metrics.
node_coverage = len(nodes)
edge_coverage = len(edges)
edge_pair_coverage = len(edge_pairs)

# Printing coverage information.
print(f"Node Coverage: {node_coverage}")
print(f"Edge Coverage: {edge_coverage}")
print(f"Edge-Pair Coverage: {edge_pair_coverage}")

# Taking a screenshot at the end of the test.
result = device.takeSnapshot()
result.writeToFile('myproject/finalState.png', 'png')
