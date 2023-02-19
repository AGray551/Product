public class Product {
        private String ID;
        private String Name;
        private String Description;
        private double Cost;
        static private int IDSeed =  1;

        public static void setIDSeed(int IDSeed) {
            Product.IDSeed = IDSeed;
        }

        public static int getIDSeed() {
            return IDSeed;
        }

        public Product(String ID, String Name, String Description, String titleName, double Cost)
        {
            this.ID = ID;
            this.Name = Name;
            this.Description = Description;
            this.Cost = Cost;
        }

        public Product(String Name, String Description, String titleName, double Cost)
        {
            this.ID = this.genID();
            this.Name = Name;
            this.Description = Description;
            this.Cost = Cost;
        }


        public String getID() {
            return ID;
        }

        private String genID() {
            String newID = "" + IDSeed;
            while(newID.length() < 6)
            {
                newID = "0" + newID;
            }

            IDSeed++;

            return newID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public double getCost() {
            return Cost;
        }

        public void setCost(double Cost) {
            this.Cost = Cost;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "ID='" + ID + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Description='" + Description + '\'' +
                    ", Cost=" + Cost +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Cost == product.Cost && ID.equals(product.ID) && Name.equals(product.Name) && Description.equals(product.Description);
        }

        public String toJSONRecord()
        {
            String retString = "";
            char DQ = '\u0022';  // Assign the double quote char to a variable
            retString =  "{" + DQ + "ID" + DQ + ":" + DQ + this.ID + DQ + ",";
            retString += DQ + "Name" + DQ + ":" + DQ + this.Name + DQ + ",";
            retString += " " + DQ + "Description"  + DQ + ":" + DQ + this.Description + DQ + ",";
            retString += " " + DQ + "Cost"  + DQ + ":" + this.Cost + "}";

            return retString;
        }

        public String toXMLRecord()
        {
            String retString = "";

            retString = "<Product>" + "<ID>" + this.ID + "</ID>";
            retString += "<Name>" + this.Name + "</Name>";
            retString += "<Description>" + this.Description + "</Description>";
            retString += "<Cost>" + this.Cost + "</Cost></Product>";

            return retString;
        }
        public String toCSVRecord() {
            return  this.ID + ", " + this.Name + "," + this.Description + "," + Cost;
    }
}
