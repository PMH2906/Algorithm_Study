#include <iostream> // 전처리 지시자
#include <cstring> //strlen() 함수를 사용하기 위해서

// std::cout/ std:end1을 사용을 쉽게 cout/endl로 간단하게 쓰기 위해
using namespace std;

int main(){
    //C++ 코드에는 반드시 main의 이름을 가지고 있는 함수가 있어야한다.
    // endl은 줄바꿈
    // cout 다음 문장을 출력해줌
    cout<<"Hellow, World"<<endl;

    // 자료형 : int, shor, long, long long, char='값'("":큰 문자는 char형에 사용 불가능), bool
    char a[] = {'a','b','c','\0'};// '\0'은 Null을 의미. 넣어주지 않으면 Null을 만날때까지 메모리 셋팅됨
    // " "는 안에 명시적을 null 문자가 표함 => String 형태
    char b ='a';

    bool c = 0; // bool형은 0 외에 모두 1로 저장. 만약 10을 삽입하면 1로 저장


    // 바뀔 필요가 없는 수/ 바뀌어서는 안되는 수 : 상수=>상수는 선언하면서 초기화해줘야함
    const float PIE = 3.1415926;
    //원의 넓이 r*r*pi
    int r =3;
    float s= r*r*PIE;
    
    // 데이터형 변환
    // 1. 특정 데이터형의 변수에 다른 데이터형의 값을 대입했을 때
    int a =3.121592;
    cout<<a<<endl; //3으로 출력

    // 1-1. 강제적으로 데이터형 변환
    char ch ='M';
    cout<<(int)ch<<" "<<int(ch)<<endl; // 둘다 int 형으로 출력
    cout<< static_cast<int>(ch)<<endl;  //static_cast<typeName>(변수명) 으로 데이터 타입 변환

    // 2.수식에 데이터형을 혼합하여 사용했을 때
    // 3. 함수에 매개변수를 전달할때

    // - + * /(나누기 몫.10%3=3) %(나누기 나머지. 부동소수점 즉, 실수에서는 사용 불가)
    int b = 10;
    int c = 3;
    float d= 5.0;
    cout<<b/c<<endl; // 3 출력
    cout<<b/d<<endl; //2.0으로 실수로 출력. 피연산자 중 하나라도 float으로 실수면 실수로 몫만이 아닌 모든 값 출력

    //auto : 좋지않음
    auto e =100; // int형으로 자동
    auto f = 1.5; // float 형으로 자동
    auto g = 1.3e12L; //long long 형 으로 자동

    
    // c++는 복합데이터형을 제공 : 사용자 정의대로 새로운 데이터형을 만들 수 있다
    // 복합데이터형 : 기본 정수형(int, char 등) 과 부동소수점형의 조합

    // 배열:같은 데이터형의 집합
    // typeName arryaName[arraySize];
    // 배열은 초기화를 선언 이후 나중에 할 수 X
    // 배열을 다른 배열에 통째로 대입X
    // ex) short month[5]={...};
    //     short year[12]={...};
    //     year=monty; 불가능
    // 초기화 값의 개수를 배열 원소의 개수보다 모자리게 제공가능
    // 배열을 부분적으로 초기화하면, 나머지 원소들은 모두 0으로 설정
    // 즉, 명시적으로 첫 번째 원소만 0으로 초기화하면, 나머지 원소들은 모두 0으로 초기화된다.
    // 배열을 초기화할때 대괄호 속을 비워두면 컴파일러가 초기화 값의 개수를 헤아려 배열원소 개수 저장 
    short month[12] = {1, 2, 3};

    // 문자열
    char a[6] = "Hello"; //명시적으로 ""는 마지막에 \0(NULL)값이 있으므로 뒤에 메모리 안 참.

     //사용자 입력
    const int Size = 15;
    char name1[Size];
    char name2[Size] = "C++programing";

    cout << "안녕하세요! 저는 " << name2;
    cout << "입니다! 성함이 어떻게 되시나요?\n";
    cin >> name1; // 사용지 입력. 만약 white space쓰면 그 전까지만 입력됨. ex) park mihee면 park만 저장
    //cin.get(name1, Size);
    // getline(변수명, Size크기 명시) : Size크기 만큼 white space(공백)도 저장
    // get(변수명, Size크기 명시) 도 동일 
    cin.getline(name1,Size);
    cin.get(name1,Size);
    cout << "음, " << name1 << "씨, 당신의 이름은 ";
    cout << strlen(name1) << "자입니다만\n";
    cout << sizeof(name1) << "바이트 크기의 배열에 저장되었습니다. \n";
    cout << "이름이 " << name1[0] << "자로 시작하는군요.\n";
    name2[3] = '\0';  // 3index에 null 추가 
    cout << "제 이름의 처음 세 문자는 다음과 같습니다: "; 
    cout << name2 << endl; // 3index에 null추가했으므로 거기까지 출력

    // 문자열을 다루는 방법 중 하나인 string
    // string객체에 키보드 입력 저장 가능
    // cin을 사용하여 string객체에 디스플레이 가능
    // 배열 표기를 사용하여 string 객체에 저장되어 있는 개별적인 문자에 접근 가능

    // 배열을 다른 배열에 통째로 대입할 수 없지만, 
    // string은 가능

    char char1[20];
    char char2[20]="jauar";
    string str1;
    string str2="panda";
    // char1=char2; //틀림
    str1 = str2; // 맞음
    cout << str1[0] << endl;
    return 0;

    // 구조체 : 
    // 축구선수
    struct Mystruct 
    {
        string name;
        string position;
        float height;
        float weight;
    } C;
    // 초기화 방법0. : 구조체 뒤에 변수 이름 써주면 됨

    //초기화 방법1
    Mystruct A;
    A.name = "Son";
    A.position="Striker";
    A.height = 183;
    A.weight = 77;

    // 초기화 방법2
    Mystruct B={
        "Park",
        "Striker",
        183,
        77
    };
    
    C ={

    }; // 변수도 배열과 마찬가지로 값 없이 초기화하면 0출력

    // 구조체도 배열 선언 가능
    Mystruct D[2] = {
        {"A","A",1,1},
        {"B","B",2,2},
    };
    D[0].height; // 1
    D[1].name;//"B"

    // 공용체:서로 다른 데이터형을 한 번에 한가지만 보관가능
    // 공용체는 구조체와 다르게 한 번에 한가지만 보관 가능
    union MyUnion
    {
        // if inVal을 할당한 후, longVal을 할당하면, 그 전에 할당한 inVal 값은 사라짐.
        // 따라서 메모리 절약
        int intVal;
        long longVal;
        float floatVal;
    };
    MyUnion test;

    // 열거체(enum)
    // 기호 상수를 만드는 것에 대한 또 다른 방법.
    // 열거자들의 값을 정수로만 초기화 가능
    // 초기화하지 않은 열거자들은 그 전 열거자의 +1값을 가지게 됨
    enum spectrum {red=0, orange, yellow, green=4};
    // 1. spectrum 을 새로운 데이터형 이름으로 만듬.
    // 2. red, orange, yello, .. 0에서부터3까지 정수 값을 각각 나타내는 기호 상수로 만듬

    spectrum a = orange; //a는 1 출력. spectrum에만 있는 데이터만 사용 가능
    // spectrum a = orange + yellow; 열거자들끼리의 연산(1+2는 불가) 

    int b; // int형. 따로 선언해준 후 연산 가능
    b=red; //int형인 b안에 red 열거자 선언했으므로 0대입
    b=red+3; // 0+3하여 3출력. 


    // 반복문
    // 1.반복문에 사용할 카운터의 값을 초기화(i)
    // 2.반복문을 진행할 것인지 '조건 검사'
    // 3.반복문 몸체 수행
    // 4.카운터의 값을 변화
    char array[10] = {1,2, 3, 4, 5, 6};
    // int i는 for문에서만 사용 가능. for문 밖에서 i 변수는 사라짐
    for (int i=0;i<5;i++){
        //code
        cout<<i<<"번째입니다"<<endl;
    }


    // 증가, 감소연산자
    // ++a : 변수 값을 증가시키고 판단. 즉, 증가시키고 출력
    // a++: 변수 값을 판단하고 증가. 즉, 출력하고 증가

    int j=0; // while문의 사용변수는 앞에 따로 먼저 선언되어야함
    while(j<3){
        // code
        j++;
    }
    while(true){
        // code
    }
    // 먼저 실행 후, 조건 확인함
    do{
        cout<<"while문입니다.\n";
        j++;
    }while(j);

    // 배열 기반 반복문
    int aa[5] = {1,3,5,7,9};
    for(int i;i<5;i++){
        cout<<a[i];//13579출력
    }
    for(int i : a){
        cout<<i;//13579출력
        // 만약 배열 선언 후 할당할 시, 배열의 크기 만큼 할당하지 않으면 나머지 원소는 0으로 출력 
    }

    //중첩 루프
    int temp[4][5]={{1,2,3,4,5},{11,22,33,44,55},}; // 4행 5열
    for(int row=0;row<4;row++){
        for(int col=0;col<5;col++){
            cout<<temp[row][col]<<endl;
        }
    }
    
    // 분기구문:ifans
    if(true){
        // code
    }
    else if(false){
        // code
    }
    else{
        // code
    }
    if(true) cout<<"거짓입니다.";

    // 논리표현식
    // 논리합, 논리곱, 논리부정 연산자
    
}