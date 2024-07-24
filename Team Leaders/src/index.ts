import express, { Request, Response } from "express";
import { AppDataSource } from "./config/connection-db";
import { error } from "console";
import teamLeaderRouter from "./router/team-leader.route";
import { client } from "./config/eureka-client";

const app = express();
app.use(express.json());

app.get("/", (req: Request, res: Response) => {
  res.json({ message: "Hello World!" });
});

app.use("api/v1/team-leader", teamLeaderRouter);

AppDataSource.initialize()
  .then(() => {
    console.log("Database connected");

    app.listen(7000, () => {
      console.log("Server is running on port 7000");
      client.start((error) => {
        console.log(error || "Eureka Client started");
      });
    });
  })
  .catch((error) => {
    console.log("Error connecting to the database ", error);
  });
