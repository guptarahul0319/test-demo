var i=0, j=0, size=0, temp=0;

size = prompt("Number of elements in array");

var numbers = [];

for (i = 0; i < size; i++)
{
    numbers[i] = prompt("Enter the array elements");
}

document.write("The unsorted array is");
document.write("<br>");

for (i = 0; i < size; i++)
{
    document.write(numbers[i]+"\t\t");
}

document.write("<br>");

for (i = 0; i < size-1; i++)
{ 
   for (j = 0; j < size-i-1; j++)
   { 
        if (numbers[j] > numbers[j+1])
        {
             temp = numbers[j];
             numbers[j] = numbers[j+1];
             numbers[j+1] = temp;
        }
    }
}

document.write("The sorted array is");
document.write("<br>");

for (i = 0; i < size; i++)
{
    document.write(numbers[i]+"\t\t");
}

