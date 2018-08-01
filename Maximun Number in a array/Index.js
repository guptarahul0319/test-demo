var numbers= [], i=0;;
var size= prompt('Enter Element');

for(i=0;i<size;i++)
{
    numbers[i]= prompt("Enter Elements os array");
}

var maximum=0;

for(i=0;i<size;i++)
{
    if(maximum < numbers[i])
    {
        maximum=numbers[i];
    }
}

alert("Maximum number in the array is= "+maximum);