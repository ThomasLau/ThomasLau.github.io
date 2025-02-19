#include <stdio.h>
#include <math.h>
#include <stdlib.h> 
#include <time.h>

double FLT_MIN = 1e-7;

double sqrt_newton(int x) {
    if(x <= 0)  return 0;
    double res, lastres;
    res = x;    //初始值，可以为任意非0的值
    do{
        lastres = res;
        res = (res + x/res)/2;
    }while(fabs(lastres-res) > FLT_MIN);
    return res;
}

double sqrt_newton_int(int x) {
    if(x <= 0)  return 0;
    double res, lastres;
    res = x;
    int last = 0;
    do{
        lastres = res;
        res = (res + x/res)/2;
        int cur = (int)res;
        if (last == cur){
            return res;
        }
        last = cur;
    }while(fabs(lastres-res) > FLT_MIN);
    return res;
}

float InvSqrt(float x){
    float xhalf = 0.5f * x;
    int i = *(int*)&x; 
    i = 0x5f375a86 - (i>>1); 
    x = *(float*)&i;
    x = x*(1.5f-xhalf*x*x); 
    x = x*(1.5f-xhalf*x*x); 
    x = x*(1.5f-xhalf*x*x);
    return 1/x;
}

int main(int argc, char **argv){
    clock_t begin, end;
    double num = atoi(argv[1]);
    double res1=0,res2=0,res4=0;
    float res3=0;
    int i;
    float num_f;
    int loopcnts = 1000000;
    if(sizeof(argv)>2){
        loopcnts = atoi(argv[2]);
    }
 
    begin = clock();
    for(i = 0; i < loopcnts; i++){
        res1+=sqrt_newton_int(num+i);
        res2+=sqrt_newton(num+i);
        res3+=InvSqrt(num+i);
        res4+=sqrt(num+i);
    }
    end = clock();
    printf("hot %f\t:%f,%f,%f,%f\n", (double)(end-begin),res1,res2,res3,res4);

    res1=0;
    double res, lastres;
    begin = clock();
    for(i = 0; i < loopcnts; i++){
        int cc=num+i;
        res = cc;
        do{
            lastres = res;
            res = (res + cc/res)/2;
        }while(fabs(lastres-res) > FLT_MIN);
        res1+=res;
    }
    end = clock();
    printf(" newton_inl(%f) = %f, \t\tcost: %f\n", num, res1, (double)(end-begin));

    res1=res2=res3=res4=0;
    begin = clock();
    for(i = 0; i < loopcnts; i++)
        res1 += sqrt_newton(num+i);
    end = clock();
    printf(" newton_cos(%f) = %f, \t\tcost: %f\n", num, res1, (double)(end-begin));
 
    begin = clock();
    for(i = 0; i < loopcnts; i++)
        res2 += sqrt_newton_int(num+i);
    end = clock();
    printf("newton_int(%f) = %f, \t\tcost: %f\n", num, res2, (double)(end-begin));
 
    num_f=1.0f*num;
    begin = clock();
    for(i = 0; i < loopcnts; i++)
        res3 += InvSqrt(num+i);
    end = clock();
    printf(" InvSqrt(%f) = %f, \t\tcost: %f\n", num, res3, (double)(end-begin));
 
    begin = clock();
    for(i = 0; i < loopcnts; i++)
        res4 += sqrt(num+i);
    end = clock();
    printf("sys sqrt(%f) = %f, \t\tcost: %f\n", num, res4, (double)(end-begin));
    // printf("res:%f, %f, %f, %f", res1,res2,res3,res4);
}

