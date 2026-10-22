public class Car 
{
    private String color;
    private String model;
    private double engine;
    private int doors;

    
    public Car()
    {
        this("Bilgi Yok","Bilgi Yok",0,0);
    }
    public Car(String color,String model,double engine,int doors)
    {
        this.color=color; 
        this.model=model;
        this.engine=engine;
        this.setDoor(doors);
    }
    public Car(String color, String model)
    {
        this(color,model,0,0);
    }

    public void setColor(String color)
    {
         this.color=color;
    }
    public void setModel(String model)
    {
        this.model=model;
    }
    public void setEngine(double engine)
    {
        this.engine=engine;
    }
    public void setDoor(int doors)
    {
        if(doors==2 || doors==4)
        {
            this.doors=doors;
        }
        else
        {
             System.out.println("Kapi sayisi 2 veya 4 olabilir yalnizca.");
        }
        
    }
    public void showInfos()
    {
        System.out.println("\nArabanin Rengi:"+this.color);
        System.out.println("Arabanin Modeli:"+this.model);
        System.out.println("Arabanin motor hacmi:"+this.engine);
        System.out.println("Arabanin kapi sayisi"+this.doors); 
    }

    public String getColor()
    {
        return this.color;
    }
    public String getModel()
    {
        return this.model;
    }
    public Double getEngine()
    {
        return this.engine;
    }
    public int getDoors()
    {
        return this.doors;
    }

    
}

/*

*/