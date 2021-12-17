/* eslint-disable @typescript-eslint/no-var-requires */
const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");

const userPaymentDetails = require("./data/user-payment-details");
const sellerPaymentList = require("./data/user-payments");

/* create new express app */
const app = express();

app.use(
  cors({
    credentials: false
  })
);

/* prevent cross-origin error */
app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Credentials", false);
  res.header("Access-Control-Allow-Headers", "Origin, Content-Type, X-Requested-With, Accept");
  next();
});

/* parse the body of the request */
app.use(bodyParser.json());
app.use(
  bodyParser.urlencoded({
    extended: true
  })
);

app.get("/users/payment/profile", (req, res) => {
  if (req.query === "") {
    res.status(400).json({
      message: "The req.query is empty"
    });
  }

  return res.json(userPaymentDetails);
});

app.put("/users/payment/profile/:paymentMethod", (req, res) => {
  const updatedUserDetails = req.body;
  return res.json(updatedUserDetails);
});

app.get("/users/payments", (req, res) => {
  if (req.query === "") {
    res.status(400).json({
      message: "The req.query is empty"
    });
  }
  return res.json(sellerPaymentList);
});

/** for serving static files */
app.use(express.static(__dirname));

/* express server runs at port :3002 */
app.listen(3002, () => {
  console.info("Express mock server listening on port 3002!");
});

module.exports = app;
