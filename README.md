# Tennis Scoreboard 🎾

The Tennis Scoreboard is a simple application aimed at keeping track of scores in a tennis match. Application also enables users to create new matches, view finished matches, search for matches by player names


## Features ✍

- Allows users to record points for both players in a tennis match.
- Displays the current score for each player.
- Automatically detects when a player wins a game or a set.

## Motivation ❌
✔️ Creating a client-server application with a web interface

✔️ Gaining practical experience with ORM using Hibernate

✔️ Designing a simple web interface without external libraries

✔️ Understanding the MVC(S) architectural pattern


## Usage 🧰

Once the application is running, follow these instructions:

1. Enter the name of each player.
2. Start entering points for each player by typing 1 for player 1 or 2 for player 2.
3. The current score will be displayed after each point.
4. The application will automatically detect when a player wins a game or a set.

## Database 🧠

#### Table `Players`
<table>
    <tr>
        <th>Column</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>ID</td>
        <td>Int</td>
        <td>Auto Increment, Primary Key</td>
    </tr>
    <tr>
        <td>Name</td>
        <td>Varchar</td>
        <td>Index</td>
    </tr>
</table>

#### Table `Matches`
<table>
    <tr>
        <th>Column</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>ID</td>
        <td>Int</td>
        <td>Auto Increment, Primary Key</td>
    </tr>
    <tr>
        <td>Player1</td>
        <td>Int</td>
        <td>References to Players.ID</td>
    </tr>
    <tr>
        <td>Player2</td>
        <td>Int</td>
        <td>References to Players.ID</td>
    </tr>
    <tr>
        <td>Winner</td>
        <td>Int</td>
        <td>References to Players.ID</td>
    </tr>
</table>
