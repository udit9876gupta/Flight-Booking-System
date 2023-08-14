const express = require("express");

const app = express();
const port = process.env.PORT || 5000;
var cors = require('cors')

app.use(cors()) // Use this after the variable declaration

// middlewares
app.use(express.json({ extended: false }));

// route included
app.use("/payment", require("./routes/payment"));

app.listen(port, () => console.log(`server started on port ${port}`));