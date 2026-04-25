# Bank Management System — Spring Boot + MySQL (Docker)

## Stack
- Spring Boot 3.2 (no JPA — raw SQL via JdbcTemplate)
- MySQL 8 (Docker)
- Java 17

## Run
```bash
docker-compose up --build
```
App starts at http://localhost:8080

---

## API Reference

### Customers  `/customers`
| Method | URL | Body |
|--------|-----|------|
| GET | /customers | — |
| GET | /customers/{id} | — |
| POST | /customers | `{"name":"","address":"","phone":""}` |
| PUT | /customers/{id} | `{"name":"","address":"","phone":""}` |
| DELETE | /customers/{id} | — |

### Branches  `/branches`
| Method | URL | Body |
|--------|-----|------|
| GET | /branches | — |
| GET | /branches/{id} | — |
| POST | /branches | `{"branchName":""}` |
| PUT | /branches/{id} | `{"branchName":""}` |
| DELETE | /branches/{id} | — |

### Accounts  `/accounts`
| Method | URL | Body |
|--------|-----|------|
| GET | /accounts | — |
| GET | /accounts/{id} | — |
| GET | /accounts/customer/{customerId} | — |
| POST | /accounts | `{"accountType":"SAVINGS","balance":0,"customerId":1,"branchId":1}` |
| PUT | /accounts/{id} | `{"accountType":"","balance":0,"customerId":1,"branchId":1}` |
| DELETE | /accounts/{id} | — |

### Transactions  `/transactions`
| Method | URL | Body |
|--------|-----|------|
| GET | /transactions | — |
| GET | /transactions/{id} | — |
| GET | /transactions/account/{accountNo} | — |
| POST | /transactions | `{"amount":500,"type":"DEPOSIT","accountNo":1}` |
| PUT | /transactions/{id} | `{"date":"2024-01-01","amount":100,"type":"WITHDRAWAL","accountNo":1}` |
| DELETE | /transactions/{id} | — |

### Transaction types: `DEPOSIT`, `WITHDRAWAL`, `TRANSFER`
- DEPOSIT → balance increases automatically
- WITHDRAWAL → balance decreases (fails if insufficient funds)
