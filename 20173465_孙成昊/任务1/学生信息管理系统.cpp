#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct STU  //ѧ���ڵ�
{
	char arrStuNum[10];
	char arrStuName[10];
	int iStuScore;
	struct _STU* pNext;
} STUNODE;

STUNODE* g_pHead = NULL;  //���������ͷ��β
STUNODE* g_pEnd = NULL;

void AddStuMSG(char *arrStuNum, char arrStuName[10], int iStuScore);  //����β���һ��ѧ����Ϣ

void AddStuMSGToLinkHead(char *arrStuNum, char arrStuName[10], int iStuScore);	//����ͷ���һ���ڵ�
 
void FreeLinkData();  //�������

void ShowStuData();  //��ӡ����  

void ShowOrder();	//��ʾָ�� 

void InsertNode(STUNODE* pTemp, char *arrStuNum, char arrStuName[10], int iStuScore);	//ָ��λ�ò���ڵ� 

void DeleteStuNode(STUNODE* pNode);	//ɾ��ָ��ѧ��

void SaveStuToFile();		//���浽�ļ��� 

void ReadStuFromFile();		//���ļ��ж�ȡѧ����Ϣ 
 
STUNODE* FindStuByNum(char* arrStuNum);		//����ָ��ѧ�� 
 
int main(void)
{
	int nOrder = -1;  //��ʼ��
	char arrStuNum[10] = {'\0'};
	char arrStuName[10] = {'\0'};
	int iStuScore = -1;
	int nFlag = 1;
	STUNODE* pTemp = NULL;
	
	ShowOrder();		//���ò˵����� 
	ReadStuFromFile();		//ϵͳ����ʱ�Զ����ļ��ж�ȡѧ����Ϣ 
	
	while(nFlag)
	{
		printf("������ָ��:");
		scanf("%d",&nOrder);
 
 		switch(nOrder)
 		{
 	 	 case 1:
			printf("����ѧ�ţ�");  //β���һ��ѧ����Ϣ
			scanf("%s",arrStuNum);
			printf("����������");
			scanf("%s",arrStuName);
			printf("����ɼ���");
			scanf("%d",&iStuScore);		 
 			AddStuMSG(arrStuNum,arrStuName,iStuScore); 
 		 break;
 		 
 	 	 case 11:
 	 		printf("����ѧ�ţ�");  //ͷ���һ��ѧ����Ϣ
			scanf("%s",arrStuNum);
			printf("����������");
			scanf("%s",arrStuName);
			printf("����ɼ���");
			scanf("%d",&iStuScore);	
			AddStuMSGToLinkHead(arrStuNum, arrStuName, iStuScore);
		 break;
		 
		 case 111:		 
		 	printf("����ָ��ѧ�ţ�");	//�м����һ��ѧ����Ϣ
			scanf("%s", arrStuNum);
			pTemp = FindStuByNum(arrStuNum);
			if(NULL != pTemp)
			{
				printf("����ѧ�ţ�");  
				scanf("%s",arrStuNum);
				printf("����������");
				scanf("%s",arrStuName);
				printf("����ɼ���");
				scanf("%d",&iStuScore);	
				
				InsertNode(pTemp, arrStuNum, arrStuName, iStuScore);	
			}	 
		 break; 
		 
		 case 2:	//��ӡָ��ѧ������Ϣ
		 	printf("����ָ��ѧ�ţ�");		//����һ��ѧ��
		 	scanf("%s", arrStuNum);
		 	pTemp = FindStuByNum(arrStuNum);		//����
		 	if(NULL != pTemp)
		 	{
		 		printf("ѧ�ţ�%s��������%s��������%d\n",pTemp->arrStuNum,pTemp->arrStuName,pTemp->iStuScore);
		 	}
		 break; 
		 
		 case 3:	//�޸�ָ��ѧ����Ϣ
		 	printf("����ѧ�ţ�");
			scanf("%s",arrStuNum);
			pTemp = FindStuByNum(arrStuNum);		//����
			
			if(NULL != pTemp)
			{	
				printf("������ѧ�ţ�"); 
				scanf("%s", arrStuNum);		//�޸�ѧ�� 
				strcpy(pTemp->arrStuNum, arrStuNum);
				
				printf("������������"); 
				scanf("%s", arrStuName);		//�޸����� 
				strcpy(pTemp->arrStuName, arrStuName);
				
				printf("�����������"); 
				scanf("%d", &iStuScore);		//�޸ĳɼ� 
				pTemp->iStuScore = iStuScore;						
			} 
		 break;
		 
		 case 4:		//���浽�ļ��� 
		 	SaveStuToFile();
		 break;
		 
		 case 6:
		 	printf("������Ҫɾ��ѧ����ѧ�ţ�"); 
			scanf("%s", arrStuNum);
			
			pTemp = FindStuByNum(arrStuNum);	//����
			
			if(NULL != pTemp)		//ɾ���ڵ� 
			{
				DeleteStuNode(pTemp);
			} 
		 break;	
		 
		 case 7:
		 	//�ͷ�
			FreeLinkData();
			g_pHead = NULL;
			g_pEnd = NULL;  
			//�ָ�����ӽڵ�
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
 	 	 printf("����ָ�����\n");
		}
	} 
	 SaveStuToFile();	//�������ļ� 
	 FreeLinkData();  //�ͷ����� 
	 system("pause");
	 return 0;	
}

void AddStuMSG(char arrStuNum[10], char arrStuName[10], int iStuScore)  //�������� 
{
	//�߼�
	//����һ���ڵ�
	STUNODE* pTemp = malloc (sizeof (STUNODE));
	
	//��һ������������Ϸ���
	if(NULL == arrStuNum || NULL == arrStuName ||iStuScore < 0)
	{
		printf("ѧ����Ϣ�������");
		return; 
	} 
		
	strcpy(pTemp->arrStuNum, arrStuNum);	//�ڵ��Ա����ʼֵ
	strcpy(pTemp->arrStuName, arrStuName);
	pTemp->iStuScore = iStuScore;
	pTemp->pNext = NULL;
	
	//����������
	if(NULL == g_pHead || NULL == g_pEnd)
	{
		g_pHead = pTemp;
		//g_pEnd = pTemp;
	}
	else
	{
		g_pEnd->pNext = pTemp;  //����
		//g_pEnd = pTemp;  //����ƶ� 
	}
	g_pEnd = pTemp;  //����
}

//�������
void FreeLinkData()
{
	STUNODE* pTemp = g_pHead;
	while(g_pHead != NULL)
	{	
		pTemp = g_pHead;	//��¼�ڵ� 		
		g_pHead = g_pHead -> pNext;	//����ƶ�һ�� 
		free(pTemp);	//ɾ���ڵ� 
	}
}	 

void ShowStuData()	//��ӡ����
{
	STUNODE* pTemp = g_pHead;
	while (pTemp != NULL)
	{
		printf("ѧ��:%s,����:%s,����:%d\n",pTemp->arrStuNum,pTemp->arrStuName,pTemp->iStuScore);	
		pTemp = pTemp->pNext;	//������һ�� 
	}
}

void ShowOrder()	//��ʾָ�� 	
{
	printf("***********����ָ������***********\n");
	printf("1������һ��ѧ����Ϣ(β���)\n");
	printf("11������һ��ѧ����Ϣ(ͷ���)\n");
	printf("111������һ��ѧ����Ϣ(��ָ��λ�����)\n");
	printf("2������ָ��ѧ����Ϣ������/ѧ�ţ�\n");
	printf("3���޸�ָ��ѧ����Ϣ\n"); 
	printf("4������ѧ����Ϣ���ļ���\n"); 
	printf("5����ȡ�ļ��е�ѧ����Ϣ\n"); 
	printf("6��ɾ��ָ��ѧ������Ϣ\n"); 
	printf("7���ָ���ɾ����ѧ����Ϣ\n"); 
	printf("8����ʾ����ѧ����Ϣ\n"); 
	printf("9����ʾָ��˵�\n");
	printf("0���˳�ϵͳ\n"); 
} 
	
void AddStuMSGToLinkHead(char *arrStuNum, char arrStuName[10], int iStuScore)	//����ͷ���һ���ڵ�
{
	STUNODE* pTemp = malloc(sizeof(STUNODE));	//����һ���ڵ�
	if(NULL == arrStuName || NULL == arrStuNum || iStuScore <0)	//��������Ϸ���
	{
		printf("ѧ����Ϣ�������\n");
		return; 
	}	 
	
	strcpy(pTemp->arrStuNum, arrStuNum);	//�ڵ��Ա����ʼֵ
	strcpy(pTemp->arrStuName, arrStuName);
	pTemp->iStuScore = iStuScore;
	pTemp->pNext = NULL;
	
	if (NULL == g_pHead || NULL == g_pEnd)	//�������Ϊ�� 
	{
		g_pHead = pTemp;
		g_pEnd = pTemp;	
	} 
	else
	{
		pTemp->pNext = g_pHead;	//�½ڵ����һ��ָ��ͷ 
		g_pHead = pTemp;	//ͷ��ǰŲ��һ�� 
	}
}
 
STUNODE* FindStuByNum(char* arrStuNum)	//����ָ��ѧ�� 
{
	STUNODE* pTemp = g_pHead; 
	if (NULL == arrStuNum)		//��������Ϸ��� 
	{
		printf("ѧ���������\n");
		return; 
	} 
	
	if (NULL == g_pHead || NULL == g_pHead)		//�ж������Ƿ�Ϊ�� 
	{
		printf("����Ϊ�գ�\n");
		return NULL; 	
	} 
	
	while(pTemp != NULL)		//��������������Ҳ��ֱ��д��(pTemp)
	{//strcmp:�Ƚ������ַ��������������ַ���Ϊstr1��str2����str1==str2���򷵻��㣻��str1<str2���򷵻ظ�������str1>str2���򷵻�������
		if (0 == strcmp(pTemp->arrStuNum, arrStuNum))
		{
			return pTemp;
		}
		pTemp = pTemp->pNext;
	} 
	printf("���޴˽ڵ㣡\n");
	return NULL; 
}	

void InsertNode(STUNODE* pTemp, char *arrStuNum, char arrStuName[10], int iStuScore)		//ָ��λ�ò���ڵ� 	
{
	STUNODE* pNewTemp = malloc(sizeof(STUNODE));		//�����ڵ� 
	
	strcpy(pNewTemp->arrStuNum, arrStuNum);		//�ڵ��Ա����ʼֵ
	strcpy(pNewTemp->arrStuName, arrStuName);
	pNewTemp->iStuScore = iStuScore;
	pNewTemp->pNext = NULL;	
	
	if(pTemp == g_pEnd)		//�������λ����β�ڵ�
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
	if(g_pHead == g_pEnd)		//ֻ��һ���ڵ� 
	{
		free(g_pHead);
		g_pHead = NULL;
		g_pEnd = NULL;
	} 
	
	else if(g_pHead->pNext == g_pEnd)		//ֻ��ͷ�ڵ��β�ڵ�
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
	else		//�ڵ������ڶ� 
	{
		STUNODE* pTemp = g_pHead;		//�ж�ͷ
		if(g_pHead == pNode)
		{
			pTemp = g_pHead;		//��סͷ
			g_pHead = g_pHead->pNext;
			free(pTemp);
			pTemp = NULL;
			return; 		//���� 
		} 
		
		while(pTemp)
		{
			if(pTemp->pNext == pNode)
			{
				if (pNode == g_pEnd)		//ɾ�� 
				{
					free(pNode);
					pNode = NULL;
					g_pEnd = pTemp;
					g_pEnd->pNext = NULL;
					return;
				}
				else		//ɾ���м�ڵ� 
				{
					STUNODE* p = pTemp->pNext;		//��סҪɾ���Ľڵ� 
					pTemp->pNext = pNode->pNext;		//���� 
					free(p);		//�ͷ� 
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
	if(NULL == g_pHead)		//�ж������Ƿ�Ϊ�� 
	{
		printf("û��ѧ����Ϣ\n");
		return; 
	} 
	
	pFile = fopen("dat.txt","wb+");		//���ļ�
	if (NULL == pFile)
	{
		printf("�ļ���ʧ��\n");
		return;	
	}
	
	while(pTemp)		//�����ļ�ָ��
	{
		strcpy(strBuf,pTemp->arrStuNum);		//��ѧ�Ÿ��� 
		strcat(strBuf,".");
		strcat(strBuf,pTemp->arrStuName);		//����������ѧ�ź���strcat 
		strcat(strBuf,".");
		itoa(pTemp->iStuScore,strScore,10);		//��int�������ļ���Ҫת����char�� ,��ת����ʮ���� 
		strcat(strBuf,strScore);
		fwrite(strBuf,1,strlen(strBuf),pFile);		//д���ļ��ĺ��� 
		fwrite("\r\n",1,strlen("\r\n"),pFile);		//���� 
		pTemp = pTemp->pNext; 
	}
	fclose(pFile);		//�ر��ļ� 		 
}

void ReadStuFromFile()
{
	FILE* pFile = fopen("dat.txt","rb+");
	char strBuf[30] = {0};		//Buffer���ַ����飬�൱��һ��������װ�ض�ȡ�������� 
	
	char strStuNum[10] = {0};
	char strStuName[10] = {0};
	char strScore[10] = {0};
	
	int nCount = 0;		//����������¼����һ�� 
	
	if(NULL == pFile)		//��֤�ļ��Ƿ��ʧ�� 
	{
		printf("�ļ���ʧ��\n");
		return;
	}
	 
	//����ָ�룬��ȡ���� 
	//fgets(strBuf, 30 ,pFile);	��ȡ�ļ�������һ�ζ�ȡһ�У������ڲ�����˳��Ϊ��Buf�׵�ַ��Buf�ַ�����Ĵ�С ��pFile 
	
	while(0 != feof(pFile))
	{	
		int i = 0;
		nCount = 0;
		int j = 0;
		fgets(strBuf, 30, pFile);
		
		for(i = 0; strBuf != '\r'; i++)
		{
			if(0 == nCount)		//û��'.'
			{
				strStuNum[i] = strBuf[i]; 
				if('.' == strBuf[i])
				{
					strStuNum[i] = '\0';
					nCount++;
				}
			} 
			else if(1 == nCount)		//����һ�� '.'
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
			else		//���ڶ��� '.'
			{
				strScore[j] = strBuf[i]; 
				j++;
			}
		}
	}
	
	fclose(pFile);	
} 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
