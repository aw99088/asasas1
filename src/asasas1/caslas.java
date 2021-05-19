package asasas1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

class calc implements ActionListener {	
	JFrame frame;     //JFrame 변수 선언   
	JTextField textfieldNorth; 
	JTextField textField;
	JMenuBar menuBar;
	JMenu menu;
	JMenu menu2;
	JMenu menu3;
	JPanel panel1;  //Border 
	JPanel panelNorth;  //Border 
	GridLayout grid;
	JButton JBu;
	JButton button1;
	
	String[] strGrid={"7","8","9","/","4","5","6","X","1","2","3","-" ,"0","C","+","="};
	String[] strGrid2= {"7", "8", "9","km","4","5","6","ton","1","2","3","분","0","C","kbyte","="};
	
	private String first="";  		// 숫자 누적
	private String result=""; 		// 결과값  "=" 출력
	ArrayList<Integer> ee=new ArrayList<Integer>();  		// 값들 누적으로 받기.
	ArrayList<String> store=new ArrayList<String>();  		// 연산자 누적으로 저장.

	
	public calc(){
		frame=new JFrame("계산기");
		menuBar=new JMenuBar();

		menu=new JMenu("보기(V)");
		menu.setFont(new Font("Sans-serif", 0, 12));
		
		menu2=new JMenu("편집(E)");
		menu2.setFont(new Font("Sans-serif", 0, 12));
		
		menu3=new JMenu("도움말(H)");
		menu3.setFont(new Font("Sans-serif", 0, 12));
		
		button1=new JButton("단위 변환");
		button1.setFont(new Font("Sans-serif", 0, 12));
		button1.setBackground(new Color( 222,232,244));
		button1.addActionListener(new ActionListener() {
            // 만들어진 버튼 "새 창 띄우기"에 버튼이 눌러지면 발생하는 행동을 정의
            @Override
            public void actionPerformed(ActionEvent e) {
                new newWindow(); // 클래스 newWindow를 새로 만들어낸다
            }
        });
		
		
		panel1=new JPanel();
		panelNorth=new JPanel();
		textField=new JTextField("0"); 			// 초기값 아무것도 안보이게
		textfieldNorth=new JTextField(""); 		// 연산자만 보이는 텍스트 
	}// 생성자 calc()
		

	public void gui(){
		menuBar.add(menu);		// 메뉴바에 메뉴들 1,2,3 붙였다 
		menuBar.add(menu2);	
		menuBar.add(menu3);
		menuBar.add(button1);
		
		textField.setHorizontalAlignment(JTextField.RIGHT);   // 우측정렬
		textField.setEditable(false); 		// 텍스트필드창에 텍스트쓰지못하게 잠금
		
		textfieldNorth.setHorizontalAlignment(JTextField.RIGHT);  // 우측정렬
		textfieldNorth.setEditable(false); 	// 텍스트필드창에 텍스트쓰지못하게 잠금
		
		panelNorth.setLayout(new BorderLayout());    		// 레이아웃 설정.
		panelNorth.add(BorderLayout.NORTH,textfieldNorth);   // 패널에 텍스트필드를 두개 붙임.
		panelNorth.add(BorderLayout.CENTER,textField);
		
		panel1.setLayout(new GridLayout(4,3,6,6));  		// 그리드 레이아웃 속성설정
		panel1.setBackground(new Color( 222,232,244));  // 패널색상
	

		for(int i=0; i<strGrid.length; i++){
			JBu=new JButton(strGrid[i]);  				// 버튼 생성 
			JBu.addActionListener(this);	 			// 각 버튼마다 리스너 붙이기
			JBu.setBackground(new Color( 241,244,249));    // 버튼 집어넣기
			panel1.add(JBu); 							// 패널에 각각의 버튼들 붙이기
		}
		
	
		frame.add(BorderLayout.NORTH,panelNorth); 
		frame.add(BorderLayout.CENTER,panel1); 		 // 텍스트필드 북쪽에
		frame.setJMenuBar(menuBar);  				// 메뉴바 붙이기
		
		frame.setResizable(false);                                  //창 크기 변경 못하게 막는다.
		frame.setSize(330,310);                                    //frame 의 크기  
		frame.setVisible(true);                                   //frame을 화면에 나타나도록 설정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //X버튼 활성화 
	}//gui()

	public void actionPerformed(ActionEvent e){
		String str=e.getActionCommand();  		// 문자열로 이벤트불러옴
		String read;				// 텍스트필드에 있는 텍스트 읽기용도
		
		try{	
			//연산기호가 아닌 숫자이면 true 이다.
			if(str!="/" && str!= "X"&& str!= "-"&& str!= "+"&& str!= "C"&& str!= "="){	
				textField.setText(first);  		// 0을지우고 숫자누적하기위해 필요.
				textField.getText();  			// 누적 출력
				textfieldNorth.setText("");		// 연산자 텍스트 초기화
				textfieldNorth.getText(); 		// 출력
				
				read=textField.getText();  		// 텍스트에 있는값 불러옴.
				first=read+str;
				textField.setText(first);		// 누적 저장
				textField.getText(); 			// 누적 출력
			}// if
			
			
			
			//연산기호가 들어왔을때 true.
			if(str=="/" || str== "X"|| str== "-"|| str== "+"|| str== "="){
				textfieldNorth.setText(str); 				 // 연산자 기호 저장
				textfieldNorth.getText();  					// 연산자 기호 출력
				
				ee.add(Integer.parseInt(first));  // 연산하려는 값 하나씩 ArrayList에 저장
				first="";						// 다시 누적하도록 초기화
				
				store.add(str); 			   // 연산자 ArrayList에 저장		
			}// if
			
			//연산기호 "=" 결과값 눌렀을때.
			if(str=="="){		
				int sum=0;
				sum=ee.get(0); 		// 최초 시작값을 받고 시작 , 1(+2)(+3)(+4)=result 초기값만 알면 3번만 "()" 연산해주면 된다. 
				
				for(int a=0,h=1; a<ee.size(); a++,h++){   // ArrayList 크기만큼 반복
					int ve=store.size(); 				// 연산자 누적 횟수 저장
					if(ve>0){							// 연산자 누적 모두 사용할때까지 반복.
						ve--;			
						//if문 또는 switch문으로 가능.
						if(store.get(a)=="+"){			// 연산자 + 일때
							sum=sum+ee.get(1);  			// (+2) 두번째 숫자와 연산
						}else if(store.get(a)=="-"){	// 아래부터 반복..	
							sum=sum-ee.get(1);	
						}else if(store.get(a)=="X"){
							sum=sum*ee.get(1);
						}else if(store.get(a)=="/"){
							try{				
								sum=sum/ee.get(1);
							}catch(Exception exc){		// 나눗셈은 0으로 나눌때 예외발생한다.
								sum=0;  //에러발생시 0으로 출력.
							}
						}
					}// if
				}// for
				result=sum+"";						//총 결과값
				textField.setText(result);  		// 결과값 저장
				textField.getText(); 				// 결과출력	
			}// if
			
			//모든 값 초기화 시키고 다시 연산.
			if(str=="C"){
				first="";
				textField.setText("0"); 			 // 텍스트창 내용 지우고 0출력
				textField.getText();  			
				textfieldNorth.setText(""); 	// 연산자 텍스트창 초기화
				textfieldNorth.getText(); 
				ee.clear();  					// 모든 요소들 제거
				store.clear();  				// 모든 요소들 제거
			}
			
		}catch(Exception ex){
			textField.setText("Error 다시입력 C클릭.");  // 텍스트창 내용 전부 지우기
			textField.getText(); 
		}
	
	}// actionPerformed()
	
	class newWindow extends JFrame {
		newWindow() {
			setTitle("단위변환");
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JPanel panelf;
        JPanel panel2;
        JPanel panelNorth2;
        JTextField textField2;
        JTextField textFieldNorth2;
        JButton button2;
        
        private String first="";  		// 숫자 누적
    	private String result=""; 		// 결과값  "=" 출력
    	ArrayList<Integer> ee=new ArrayList<Integer>();  		// 값들 누적으로 받기.
    	ArrayList<String> store=new ArrayList<String>();  		// 연산자 누적으로 저장.
        
        panelf=new JPanel();
		panel2=new JPanel();
		panelNorth2=new JPanel();
		textField2=new JTextField("0"); 			// 초기값 아무것도 안보이게
		textFieldNorth2=new JTextField(""); 		// 연산자만 보이는 텍스트 
        
		textField2.setHorizontalAlignment(JTextField.RIGHT);   // 우측정렬
		textField2.setEditable(false); 		// 텍스트필드창에 텍스트쓰지못하게 잠금
		
		textFieldNorth2.setHorizontalAlignment(JTextField.RIGHT);  // 우측정렬
		textFieldNorth2.setEditable(false); 	// 텍스트필드창에 텍스트쓰지못하게 잠금
		
		panelNorth2.setLayout(new BorderLayout());    		// 레이아웃 설정.
		panelNorth2.add(BorderLayout.NORTH,textfieldNorth);   // 패널에 텍스트필드를 두개 붙임.
		panelNorth2.add(BorderLayout.CENTER,textField);
	 	
		panel2.setLayout(new GridLayout(4,3,6,6));  		// 그리드 레이아웃 속성설정
		panel2.setBackground(new Color( 222,232,244));  // 패널색상
		
		for(int i=0; i<strGrid.length; i++){
			button2=new JButton(strGrid2[i]);  				// 버튼 생성 
			button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String str=e.getActionCommand();  		// 문자열로 이벤트불러옴
					String read;				// 텍스트필드에 있는 텍스트 읽기용도
					
					try{	
						//연산기호가 아닌 숫자이면 true 이다.
						if(str!="km" && str!= "ton"&& str!= "분"&& str!= "kbyte"&& str!= "C"&& str!= "="){	
							textField2.setText(first);  		// 0을지우고 숫자누적하기위해 필요.
							textField2.getText();  			// 누적 출력
							textFieldNorth2.setText("");		// 연산자 텍스트 초기화
							textFieldNorth2.getText(); 		// 출력
							
							read=textField2.getText();  		// 텍스트에 있는값 불러옴.
							first=read+str;
							textField2.setText(first);		// 누적 저장
							textField2.getText(); 			// 누적 출력
						}// if
						
						
						
						//연산기호가 들어왔을때 true.
						if(str=="km" || str== "ton"|| str== "분"|| str== "kbyte"|| str== "="){
							textFieldNorth2.setText(str); 				 // 연산자 기호 저장
							textFieldNorth2.getText();  					// 연산자 기호 출력
							
							ee.add(Integer.parseInt(first));  // 연산하려는 값 하나씩 ArrayList에 저장
							first="";						// 다시 누적하도록 초기화
							
							store.add(str); 			   // 연산자 ArrayList에 저장		
						}// if
						
						//연산기호 "=" 결과값 눌렀을때.
						if(str=="="){		
							int sum=0;
							sum=ee.get(0); 		// 최초 시작값을 받고 시작 , 1(+2)(+3)(+4)=result 초기값만 알면 3번만 "()" 연산해주면 된다. 
							
							for(int a=0,h=1; a<ee.size(); a++,h++){   // ArrayList 크기만큼 반복
								int ve=store.size(); 				// 연산자 누적 횟수 저장
								if(ve>0){							// 연산자 누적 모두 사용할때까지 반복.
									ve--;			
									//if문 또는 switch문으로 가능.
									if(store.get(a)=="+"){			// 연산자 + 일때
										sum=sum+ee.get(1);  			// (+2) 두번째 숫자와 연산
									}else if(store.get(a)=="-"){	// 아래부터 반복..	
										sum=sum-ee.get(1);	
									}else if(store.get(a)=="X"){
										sum=sum*ee.get(1);
									}else if(store.get(a)=="/"){
										try{				
											sum=sum/ee.get(1);
										}catch(Exception exc){		// 나눗셈은 0으로 나눌때 예외발생한다.
											sum=0;  //에러발생시 0으로 출력.
										}
									}
								}// if
							}// for
							result=sum+"";						//총 결과값
							textField2.setText(result);  		// 결과값 저장
							textField2.getText(); 				// 결과출력	
						}// if
						
						//모든 값 초기화 시키고 다시 연산.
						if(str=="C"){
							first="";
							textField2.setText("0"); 			 // 텍스트창 내용 지우고 0출력
							textField2.getText();  			
							textFieldNorth2.setText(""); 	// 연산자 텍스트창 초기화
							textFieldNorth2.getText(); 
							ee.clear();  					// 모든 요소들 제거
							store.clear();  				// 모든 요소들 제거
						}
						
					}catch(Exception ex){
						textField2.setText("Error 다시입력 C클릭.");  // 텍스트창 내용 전부 지우기
						textField2.getText(); 
					}
				}				//actionPerformed()
			});	 			// 각 버튼마다 리스너 붙이기
			button2.setBackground(new Color( 241,244,249));    // 버튼 집어넣기
			panel2.add(button2); 							// 패널에 각각의 버튼들 붙이기
		}
        
		panelf.setLayout(new BorderLayout());
		panelf.add(BorderLayout.CENTER,panelNorth2);
		panelf.add(BorderLayout.SOUTH,panel2);
		NewWindowContainer.add(panelf);

		
        setSize(330,310);
        setResizable(false);
        setVisible(true);
		}
	}
	

	public static void main(String[] args){
		calc cal=new calc();
		cal.gui();
	}// main()


}// class