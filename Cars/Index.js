var cars= [];

var i=0, size=0;

size = prompt("Enter the number of cars");

for(i=0; i<size; i++)
{
    var carsobject={name : "",
                    manufacturer : "",
                    topspeed : 0};

    carsobject.name = prompt("Enter the car name");
    carsobject.manufacturer = prompt("Enter the manufacturer name");
    carsobject.topspeed = prompt("Enter the topspeed");
    
    cars[i]= carsobject;
}

for(i=0; i<size; i++)
{
    if(cars[i].topspeed >= 100)
    {
        document.write("Car Name= "+cars[i].name + "  Manufacturer Name= " +cars[i].manufacturer+"  Top Speed= "+cars[i].topspeed);
        document.write("<br>");
    }
}