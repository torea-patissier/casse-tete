
# H2 Database
Run the CasseTeteApplication, access the H2 database :
- **URL**: [http://localhost:9090/h2/](http://localhost:9090/h2/)
- **JDBC URL**: `jdbc:h2:file:./data/testdb`
- **Username**: `sa`
- **Password**: *(leave empty)*

### The operations are:
| Operation  | Value      |
|------------|------------|
| calculateA | 13 * B / C |
| calculateB | 12 * E     |
| calculateC | G * H / I  |

### The final equation is:
- A + calculateA + D + calculateB - F - 11 + calculateC - 10

## How I proceed to calculate the solution
- I start by giving a letter for each position in the equation
- I give a name for each operation (alpha, bravo, charlie)
- I calculate the value of each operation first
- Once the value of each operation is calculated, I calculate the final result to get only addition and subtraction operations

