
# H2 Database
Run the CasseTeteApplication, access the H2 database :
- **URL**: [http://localhost:9090/h2/](http://localhost:9090/h2/)
- **JDBC URL**: `jdbc:h2:file:./data/testdb`
- **Username**: `sa`
- **Password**: *(leave empty)*

# Solution
- (5||6) + [(13x9)/3] + (6||5) + (12x2) - 1 - 11 + [(7x8||8x7)/4] - 10 
- (5||6) + (117/3) + (6||5) + 24 - 1 - 11 + (56/4) - 10
- (5||6) + 39 + (6||5) + 24 - 1 - 11 + 14 - 10
- 66

### The numbers are:
| Variable | Value  |
|----------|--------|
| A        | 5 or 6 |
| B        | 9      |
| C        | 3      |
| D        | 5 or 6 |
| E        | 2      |
| F        | 1      |
| G        | 7 or 8 |
| H        | 7 or 8 |
| I        | 4      |

### The operations are:
| Operation | Value      |
|-----------|------------|
| calculA   | 13 * B / C |
| calculB   | 12 * E     |
| calculC   | G * H / I  |

### The final equation is:
- A + calculA + D + calculB - F - 11 + calculC - 10

## How I proceed to calculate the solution
- I start by giving a letter for each position in the equation
- I give a name for each operation (alpha, bravo, charlie)
- I calculate the value of each operation first
- Once the value of each operation is calculated, I calculate the final result to get only addition and subtraction operations


### Request to get the right numbers and operations
```json
{
   "gridData": "{ \"A\": 5, \"B\": 9, \"C\": 3, \"D\": 6, \"E\": 2, \"F\": 1, \"G\": 7, \"H\": 8, \"I\": 4 }"
}
```
