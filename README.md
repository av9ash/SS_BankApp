# Secure Banking System

# add a transaction  - POST
{
    “user_id” : “user-id”,
    “type” : ” [(Credit) 1 / (Debit) 2 / (Transfer) 3]”,
“transaction_amount” : “amount”,
    “from_account” : “account_id”,
    “to_account” : “account_id”
}


# approve transaction - POST
{
	"transaction_id" : "transaction-id",
	"approved_by" : "user-id"
} 

# decline transaction - POST
{
	"transaction_id" : "transaction-id",
	"declined_by" : "user-id"
}


# search account - POST
{
	"email" : "email-id",
	"phone" : "phone no"
}




