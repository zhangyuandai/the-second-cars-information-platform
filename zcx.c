#include <stdio.h>
int main()
{
int start, end, i = 0, a, b, c, size = 0; 
while (scanf("%d %d", &start, &end) == 2)
{
for (i = start; i <= end; i++)
{
	a = i / 100;
	b = i / 10 % 10;
	c = i % 10;
//total = pow(c, 3) + pow(a, 3) + pow(b, 3);
if ((a*a*a + b*b*b + c*c*c) == i)  //����ˮ�ɻ�����
{
if (size == 0)   //size=0�����һ��ˮ�ɻ��� 
{
printf("%d", i);
}
else     //size++����ڶ�������n��ˮ�ɻ���
{
printf(" %d", i);
}
size++;   //����++��
}
}
if (size == 0)   //��Χ�ڸ���Ϊ0����˵��û������������
{
printf("no");
}
printf("\n");
}
return 0;
}
