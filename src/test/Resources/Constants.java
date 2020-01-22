public class Constants {
    String sample ="{\n" +
            "  getData(func: eq(idVal," + id +")){\n" +
            "\t\t\n" +
            "    user:\t~hasAccount{\n" +
            "\n" +
            "\t\tcase: ~isfulfilledby{\n" +
            "    uid\n" +
            "   }\n" +
            "\t  \thasServiceContract{\n" +
            "      uid\n" +
            "\t\t\thasServiceHistory{\n" +
            "        uid\n" +
            "      },\n" +
            "\t\t\tisForServiceOffering{\n" +
            "        uid\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "  }\n" +
            "}"
}

{
        "accountId": ,
        "status": ,
        "name": ,
        "serviceStatus":
        }


"{\n" +
        "    \"firstName\":" + pm.environment.get('firstName') + ",\n" +
        "    \"lastName\":" + pm.environment.get('lastName') + ",\n" +
        "\n" +
        "    \"phones\": [\n" +
        "        {  \n" +
        "            \"number\": ,\n" +
        "            \"type\": ,\n" +
        "            \"order\": \n" +
        "        }\n" +
        "    ],\n" +
        "    \"isCareRecipient\": ,\n" +
        "    \"requiresTTY\": \n" +
        "}"
