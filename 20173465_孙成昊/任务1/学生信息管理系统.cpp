#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct STU  //学生节点
{
	char arrStuNum[10];
	char arrStuName[10];
	int iStuScore;
	struct _STU* pNext;
} STUNODE;

STUNODE* g_pHead = NULL;  //声明链表的头和尾
STUNODE* g_pEnd = NULL;

void AddStuMSG(char *arrStuNum, char arrStuName[10], int iStuScore);  //链表尾添加一个学生信息

void AddStuMSGToLinkHead(char *arrStuNum, char arrStuName[10], int iStuScore);	//链表头添加一个节点
 
void FreeLinkData();  //清空链表

void ShowStuData();  //打印数据  

void ShowOrder();	//显示指令 

void InsertNode(STUNODE* pTemp, char *arrStuNum, char arrStuName[10], int iStuScore);	//指定位置插入节点 

void DeleteStuNode(STUNODE* pNode);	//删除指定学生

void SaveStuToFile();		//保存到文件中 

void ReadStuFromFile();		//从文件中读取学生信息 
 
STUNODE* FindStuByNum(char* arrStuNum);		//查找指定学生 
 
int main(void)
{
	int nOrder = -1;  //初始化
	char arrStuNum[10] = {'\0'};
	char arrStuName[10] = {'\0'};
	int iStuScore = -1;
	int nFlag = 1;
	STUNODE* pTemp = NULL;
	
	ShowOrder();		//调用菜单函数 
	ReadStuFromFile();		//系统启动时自动从文件中读取学生信息 
	
	while(nFlag)
	{
		printf("请输入指令:");
		scanf("%d",&nOrder);
 
 		switch(nOrder)
 		{
 	 	 case 1:
			printf("输入学号：");  //尾添加一个学生信息
			scanf("%s",arrStuNum);
			printf("输入姓名：");
			scanf("%s",arrStuName);
			printf("输入成绩：");
			scanf("%d",&iStuScore);		 
 			AddStuMSG(arrStuNum,arrStuName,iStuScore); 
 		 break;
 		 
 	 	 case 11:
 	 		printf("输入学号：");  //头添加一个学生信息
			scanf("%s",arrStuNum);
			printf("输入姓名：");
			scanf("%s",arrStuName);
			printf("输入成绩：");
			scanf("%d",&iStuScore);	
			AddStuMSGToLinkHead(arrStuNum, arrStuName, iStuScore);
		 break;
		 
		 case 111:		 
		 	printf("输入指定学号：");	//中间添加一个学生信息
			scanf("%s", arrStuNum);
			pTemp = FindStuByNum(arrStuNum);
			if(NULL != pTemp)
			{
				printf("输入学号：");  
				scanf("%s",arrStuNum);
				printf("输入姓名：");
				scanf("%s",arrStuName);
				printf("输入成绩：");
				scanf("%d",&iStuScore);	
				
				InsertNode(pTemp, arrStuNum, arrStuName, iStuScore);	
			}	 
		 break; 
		 
		 case 2:	//打印指定学生的信息
		 	printf("输入指定学号：");		//输入一个学号
		 	scanf("%s", arrStuNum);
		 	pTemp = FindStuByNum(arrStuNum);		//查找
		 	if(NULL != pTemp)
		 	{
		 		printf("学号：%s，姓名：%s，分数：%d\n",pTemp->arrStuNum,pTemp->arrStuName,pTemp->iStuScore);
		 	}
		 break; 
		 
		 case 3:	//修改指定学生信息
		 	printf("输入学号：");
			scanf("%s",arrStuNum);
			pTemp = FindStuByNum(arrStuNum);		//查找
			
			if(NULL != pTemp)
			{	
				printf("请输入学号："); 
				scanf("%s", arrStuNum);		//修改学号 
				strcpy(pTemp->arrStuNum, arrStuNum);
				
				printf("请输入姓名："); 
				scanf("%s", arrStuName);		//修改姓名 
				strcpy(pTemp->arrStuName, arrStuName);
				
				printf("请输入分数："); 
				scanf("%d", &iStuScore);		//修改成绩 
				pTemp->iStuScore = iStuScore;						
			} 
		 break;
		 
		 case 4:		//保存到文件中 
		 	SaveStuToFile();
		 break;
		 
		 case 6:
		 	printf("请输入要删除学生的学号："); 
			scanf("%s", arrStuNum);
			
			pTemp = FindStuByNum(arrStuNum);	//查找
			
			if(NULL != pTemp)		//删除节点 
			{
				DeleteStuNode(pTemp);
			} 
		 break;	
		 
		 case 7:
		 	//释放
			FreeLinkData();
			g_pHead = NULL;
			g_pEnd = NULL;  
			//恢复，添加节点
			ReadStuFromFile(); 
		 		   
 	 	 case 8:
 	 		ShowStuData();
 	 	 break;
 	 	 
 	 	 case 9:
 			ShowOrder();
	  	 break; 
	  	 
 	 	 case 0:
 	 		nFlag = 0;
 	 	 break;
 	 		
 	 	 default:
 	 	 printf("输入指令错误！\n");
		}
	} 
	 SaveStuToFile();	//保存至文件 
	 FreeLinkData();  //释放链表 
	 system("pause");
	 return 0;	
}

void AddStuMSG(char arrStuNum[10], char arrStuName[10], int iStuScore)  //函数定义 
{
	//逻辑
	//创建一个节点
	STUNODE* pTemp = malloc (sizeof (STUNODE));
	
	//第一步，检验参数合法性
	if(NULL == arrStuNum || NULL == arrStuName ||iStuScore < 0)
	{
		printf("学生信息输入错误！");
		return; 
	} 
		
	strcpy(pTemp->arrStuNum, arrStuNum);	//节点成员赋初始值
	strcpy(pTemp->arrStuName, arrStuName);
	pTemp->iStuScore = iStuScore;
	pTemp->pNext = NULL;
	
	//接在链表上
	if(NULL == g_pHead || NULL == g_pEnd)
	{
		g_pHead = pTemp;
		//g_pEnd = pTemp;
	}
	else
	{
		g_pEnd->pNext = pTemp;  //链接
		//g_pEnd = pTemp;  //向后移动 
	}
	g_pEnd = pTemp;  //链接
}

//清空链表
void FreeLinkData()
{
	STUNODE* pTemp = g_pHead;
	while(g_pHead != NULL)
	{	
		pTemp = g_pHead;	//记录节点 		
		g_pHead = g_pHead -> pNext;	//向后移动一个 
		free(pTemp);	//删除节点 
	}
}	 

void ShowStuData()	//打印数据
{
	STUNODE* pTemp = g_pHead;
	while (pTemp != NULL)
	{
		printf("学号:%s,姓名:%s,分数:%d\n",pTemp->arrStuNum,pTemp->arrStuName,pTemp->iStuScore);	
		pTemp = pTemp->pNext;	//向下走一步 
	}
}

void ShowOrder()	//显示指令 	
{
	printf("***********操作指令如下***********\n");
	printf("1、增加一个学生信息(尾添加)\n");
	printf("11、增加一个学生信息(头添加)\n");
	printf("111、增加一个学生信息(在指定位置添加)\n");
	printf("2、查找指定学生信息（姓名/学号）\n");
	printf("3、修改指定学生信息\n"); 
	printf("4、保存学生信息到文件中\n"); 
	printf("5、读取文件中的学生信息\n"); 
	printf("6、删除指定学生的信息\n"); 
	printf("7、恢复被删除的学生信息\n"); 
	printf("8、显示所有学生信息\n"); 
	printf("9、显示指令菜单\n");
	printf("0、退出系统\n"); 
} 
	
void AddStuMSGToLinkHead(char *arrStuNum, char arrStuName[10], int iStuScore)	//链表头添加一个节点
{
	STUNODE* pTemp = malloc(sizeof(STUNODE));	//创建一个节点
	if(NULL == arrStuName || NULL == arrStuNum || iStuScore <0)	//检验参数合法性
	{
		printf("学生信息输入错误！\n");
		return; 
	}	 
	
	strcpy(pTemp->arrStuNum, arrStuNum);	//节点成员赋初始值
	strcpy(pTemp->arrStuName, arrStuName);
	pTemp->iStuScore = iStuScore;
	pTemp->pNext = NULL;
	
	if (NULL == g_pHead || NULL == g_pEnd)	//如果链表为空 
	{
		g_pHead = pTemp;
		g_pEnd = pTemp;	
	} 
	else
	{
		pTemp->pNext = g_pHead;	//新节点的下一个指向头 
		g_pHead = pTemp;	//头向前挪动一个 
	}
}
 
STUNODE* FindStuByNum(char* arrStuNum)	//查找指定学生 
{
	STUNODE* pTemp = g_pHead; 
	if (NULL == arrStuNum)		//检验参数合法性 
	{
		printf("学号输入错误！\n");
		return; 
	} 
	
	if (NULL == g_pHead || NULL == g_pHead)		//判断链表是否为空 
	{
		printf("链表为空！\n");
		return NULL; 	
	} 
	
	while(pTemp != NULL)		//遍历链表，括号内也可直接写成(pTemp)
	{//strcmp:比较两个字符串，设这两个字符串为str1，str2，若str1==str2，则返回零；若str1<str2，则返回负数；若str1>str2，则返回正数。
		if (0 == strcmp(pTemp->arrStuNum, arrStuNum))
		{
			return pTemp;
		}
		pTemp = pTemp->pNext;
	} 
	printf("查无此节点！\n");
	return NULL; 
}	

void InsertNode(STUNODE* pTemp, char *arrStuNum, char arrStuName[10], int iStuScore)		//指定位置插入节点 	
{
	STUNODE* pNewTemp = malloc(sizeof(STUNODE));		//创建节点 
	
	strcpy(pNewTemp->arrStuNum, arrStuNum);		//节点成员赋初始值
	strcpy(pNewTemp->arrStuName, arrStuName);
	pNewTemp->iStuScore = iStuScore;
	pNewTemp->pNext = NULL;	
	
	if(pTemp == g_pEnd)		//如果插入位置是尾节点
	{
		g_pEnd->pNext = pNewTemp;
		g_pEnd = pNewTemp; 
	} 
	else 
	{
		pNewTemp->pNext = pTemp->pNext;
		pTemp->pNext = pNewTemp;
	}
}

void DeleteStuNode(STUNODE* pNode)	 
{
	if(g_pHead == g_pEnd)		//只有一个节点 
	{
		free(g_pHead);
		g_pHead = NULL;
		g_pEnd = NULL;
	} 
	
	else if(g_pHead->pNext == g_pEnd)		//只有头节点和尾节点
	{
		if(g_pHead == pNode)
		{
			free(g_pHead);
			g_pHead = g_pEnd;	
		}
		else
		{
			free(g_pEnd);
			g_pEnd = g_pHead;
			g_pHead->pNext = NULL;
		}
	} 
	else		//节点数大于二 
	{
		STUNODE* pTemp = g_pHead;		//判断头
		if(g_pHead == pNode)
		{
			pTemp = g_pHead;		//记住头
			g_pHead = g_pHead->pNext;
			free(pTemp);
			pTemp = NULL;
			return; 		//结束 
		} 
		
		while(pTemp)
		{
			if(pTemp->pNext == pNode)
			{
				if (pNode == g_pEnd)		//删除 
				{
					free(pNode);
					pNode = NULL;
					g_pEnd = pTemp;
					g_pEnd->pNext = NULL;
					return;
				}
				else		//删除中间节点 
				{
					STUNODE* p = pTemp->pNext;		//记住要删除的节点 
					pTemp->pNext = pNode->pNext;		//链接 
					free(p);		//释放 
					p = NULL;
					return;				
				}
			}
			pTemp = pTemp->pNext; 
		}
		
	} 
} 

void SaveStuToFile()
{
	FILE* pFile = NULL;
	STUNODE* pTemp = g_pHead;
	char strBuf[30] = {0};
	char strScore[10] = {0};
	if(NULL == g_pHead)		//判断链表是否为空 
	{
		printf("没有学生信息\n");
		return; 
	} 
	
	pFile = fopen("dat.txt","wb+");		//打开文件
	if (NULL == pFile)
	{
		printf("文件打开失败\n");
		return;	
	}
	
	while(pTemp)		//操作文件指针
	{
		strcpy(strBuf,pTemp->arrStuNum);		//将学号复制 
		strcat(strBuf,".");
		strcat(strBuf,pTemp->arrStuName);		//将姓名接在学号后，用strcat 
		strcat(strBuf,".");
		itoa(pTemp->iStuScore,strScore,10);		//将int保存至文件需要转化成char型 ,再转化成十进制 
		strcat(strBuf,strScore);
		fwrite(strBuf,1,strlen(strBuf),pFile);		//写入文件的函数 
		fwrite("\r\n",1,strlen("\r\n"),pFile);		//换行 
		pTemp = pTemp->pNext; 
	}
	fclose(pFile);		//关闭文件 		 
}

void ReadStuFromFile()
{
	FILE* pFile = fopen("dat.txt","rb+");
	char strBuf[30] = {0};		//Buffer是字符数组，相当于一个容器，装载读取出的内容 
	
	char strStuNum[10] = {0};
	char strStuName[10] = {0};
	char strScore[10] = {0};
	
	int nCount = 0;		//声明变量记录到哪一点 
	
	if(NULL == pFile)		//验证文件是否打开失败 
	{
		printf("文件打开失败\n");
		return;
	}
	 
	//操作指针，读取函数 
	//fgets(strBuf, 30 ,pFile);	读取文件函数，一次读取一行，括号内参数按顺序为：Buf首地址、Buf字符数组的大小 、pFile 
	
	while(0 != feof(pFile))
	{	
		int i = 0;
		nCount = 0;
		int j = 0;
		fgets(strBuf, 30, pFile);
		
		for(i = 0; strBuf != '\r'; i++)
		{
			if(0 == nCount)		//没到'.'
			{
				strStuNum[i] = strBuf[i]; 
				if('.' == strBuf[i])
				{
					strStuNum[i] = '\0';
					nCount++;
				}
			} 
			else if(1 == nCount)		//到第一个 '.'
			{
				strStuName[j] = strBuf[i]; 
				j++;
				if('.' == strBuf[i])
				{
					strStuName[j] = '\0';
					nCount++;
					j = 0;
				}
				j++;
			}
			else		//到第二个 '.'
			{
				strScore[j] = strBuf[i]; 
				j++;
			}
		}
	}
	
	fclose(pFile);	
} 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
