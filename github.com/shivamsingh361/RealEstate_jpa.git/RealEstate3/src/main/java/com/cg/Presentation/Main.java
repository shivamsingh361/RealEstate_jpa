package com.cg.Presentation;

import java.util.Scanner;

import org.omg.CORBA.DynAnyPackage.Invalid;

import com.cg.DTO.Address;
import com.cg.DTO.Filter;
import com.cg.DTO.Property;
import com.cg.DTO.PropertyType;
import com.cg.DTO.User;
import com.cg.DTO.UserType;
import com.cg.Service.Service;
import com.cg.Service.ServiceImpl;
import com.cg.Service.Validation;
import com.cg.exception.PropertyException;

public class Main {

	public static void main(String[] args) {
		Service obj = new ServiceImpl();
		Decoder decode = new Decoder();
		do{
			boolean home = false;
			boolean exit = false;
			Scanner sc = new Scanner(System.in);
			Integer option = 1;
			try {
				System.out.println("Choose:\n1. Login"
						+ "\n2. Register"
						+ "\n3. About Us"
						+ "\n4. Feedback"
						+ "\n5. Exit");
				option = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Please input INTEGER only.");
			}
			switch(option){
			case 1:
				System.out.println("\n**Login Here**");
				Integer optionLogin = 1;
				try {
					System.out.println("Choose Again:\n1. Enter LogIn Details"
							+ "\n2. Forgot Pass"
							+ "\n3. Back"
							+ "\n4. Home");
					optionLogin = sc.nextInt();
				} catch (Exception e) {
					System.out.println("Please input INTEGER only.");
				}
				switch(optionLogin){
				case 1:
					while(!home && !exit){
						System.out.println("\nEnter Registered Email/Phone: ");
						String id = sc.next();
						System.out.println("\nEnter Registered Password: ");
						String pass = sc.next();
						if(obj.login(id, pass)) {
							while(!home && !exit) {
								if(obj.getUser().getType().equals(UserType.BUYER)) {
									System.out.println("***********************************************************************************");
									System.out.println("\n\t\t\tThis is User's Home\n");
									obj.userHome().stream().limit(5).forEach(System.out::print);	// display only 5  property on first page						
									System.out.println("\n\n1.Apply Filters and Search Properties"
											+ "\n2.Update Profile"
											+ "\n3.Update Password"
											+ "\n4.View Property(By ID)"
											+ "\n5.Logout");
									switch (sc.next()) {
									case "1":
										System.out.println("\n Apply Filters by providing 5 Parameters ->->->->\n");
										System.out.println("\nLocation(Enter '-' for not applying...)\t:");
										String loc = sc.next();
										System.out.println("\nMin Price(Enter '-' for not applying...)\t:");
										String min = sc.next();
										System.out.println("\nMax Price(Enter '-' for not applying...)\t:");
										String max = sc.next();
										System.out.println("\nProperty Type(Enter '-' for not applying,\r\n"
												+ " 1.VILLA,\r\n" + 
												"	2.FLAT,\r\n" + 
												"	3.APPARTMENT,\r\n" + 
												"	4.HOUSE)\t\t:");
										String propType = sc.next();
										System.out.println("\nLandmark(Enter '-' for not applying...)\t:");
										String lm = sc.next();
										System.out.println(obj.Search(new Filter(loc, min, max, decode.decodeType(propType), lm)));
										break;
									case "2":
										System.out.println("\n User's Profile ->->->->\n");
										System.out.println(obj.getUser());
										System.out.println("New Name (Enter '-' for not change)\t:");
										String name = sc.next();
										System.out.println("\nContact (Enter '-' for not change)\t:");
										String contact = sc.next();
										obj.updateUserProfile(name, contact);
										break;
									case "3":
										//input old pass, new pass and new pass again and pass it by mehtod:
										System.out.println("\n User's Profile ->->->->\n");
										System.out.println(obj.getUser());
										System.out.println("Old Password (Enter '-' for not change)\t:");
										String oldPass = sc.next();
										System.out.println("\nNew Password (Enter '-' for not change)\t:");
										String newPass = sc.next();
										System.out.println("\nNew Password Again (Enter '-' for not change)\t:");
										String newPassA = sc.next();
										if(newPass.equals(newPassA))
											obj.updatePassword(oldPass,newPass);
										else
											System.out.println("Pass didn't match!");
										break;
									case "4":
										System.out.println("Enter Property ID:");
										String propId = sc.next();
										try {
											System.out.println(obj.viewProperty(propId).viewProp());
										} catch (PropertyException e) {
											System.err.println(e.getMessage());
										}

										break;
									case "5":
										System.out.println("Log-ing Out! ---------------------------------------------------");
										obj.logout();
										home=true;
										break;
									default:
										break;
									}
								}
								else{								
									System.out.println("***********************************************************************************");
									System.out.println("\n\t\t\tThis is Admin's Home\n");
									if(obj.userHome() == null)
										System.out.println();//	// display only 5  property on first page						
									else
										obj.userHome().stream().limit(5).forEach(System.out::print);
									System.out.println("\n\n1.Add Property"
											+ "\n2.Delete Property"
											+ "\n3.Update Profile"
											+ "\n4.Update Password"
											+ "\n5.Logout");
									switch (sc.next()) {
									case "1":
										System.out.println("\n Add Property here By providing details ->->->->\n");
										System.out.println("\nLandmark(Enter '-' for not applying...)\t:");
										String landmark = sc.next();
										System.out.println("\nPrice\t:");
										String propPrice = sc.next();
										System.out.println("\nState(HouseNO City State PIN)\t:");
										String state = sc.next();
										System.out.println("\nCity(HouseNO City State PIN)\t:");
										String city = sc.next();
										System.out.println("\nHouse NO(HouseNO City State PIN)\t:");
										String houseNo = sc.next();
										System.out.println("\nLocality(HouseNO City State PIN)\t:");
										String locality = sc.next();
										Address propAddress = new Address(houseNo, city, state, locality);
										System.out.println("\nProperty Type\r\n"
												+ " 1.VILLA,\r\n" + 
												"	2.FLAT,\r\n" + 
												"	3.APPARTMENT,\r\n" + 
												"	4.HOUSE)\t\t:");
										String propType = sc.next();
										System.out.println("\nDecription About Property\t:");
										String propDescription = sc.next();
										obj.addProperty(new Property(propAddress, propPrice, landmark, propDescription,decode.decodeType(propType) ));
										break;

									case "2":
										System.out.println("\n User's Profile ->->->->\n");
										System.out.println("Enter Property Id to Delete (Enter '-' for not change)\t:");
										String propId = sc.next();
										obj.deleteProperty(propId);
										break;
									case "3":
										System.out.println("\n User's Profile ->->->->\n");
										System.out.println(obj.getUser());
										System.out.println("New Name (Enter '-' for not change)\t:");
										String name = sc.next();
										System.out.println("\nContact (Enter '-' for not change)\t:");
										String contact = sc.next();
										obj.updateUserProfile(name, contact);
										break;
									case "4":
										//input old pass, new pass and new pass again and pass it by mehtod:
										System.out.println("\n Update Password ->->->->\n");
										System.out.println(obj.getUser());
										System.out.println("Old Password \t:");
										String oldPass = sc.next();
										System.out.println("\nNew Password \t:");
										String newPass = sc.next();
										System.out.println("\nNew Password Again \t:");
										String newPassA = sc.next();
										if(newPass.equals(newPassA))
											obj.updatePassword(oldPass,newPass);
										else
											System.out.println("Pass didn't match!");
										break;
									case "5":
										System.out.println("Log-ing Out! ---------------------------------------------------");
										obj.logout();
										home=true;
										break;
									default:
										break;
									}
								}
							}
							System.out.println("Login: Try Again?(Press y)\n Else(press 'n')");
							if(!sc.next().equalsIgnoreCase("y"))
								break;
							else
								home=false;
						}
					};
					break;
				case 2:
					System.out.println("\nEnter Registered Email/Phone: ");
					String id1 = sc.next();
					Integer otpentered, otp =  obj.verifyOTP(id1);
					System.out.println("\nEnter OTP Sent, again: "+otp);
					otpentered = Integer.parseInt(sc.next());
					if(otp.equals(otpentered)){
						System.out.println("\nEnter Password: ");
						String pass1 = sc.next();
						System.out.println("\nEnter Password Again: ");
						String pass2 = sc.next();
						if(pass1.equals(pass2))
							System.out.println(obj.forgotPassword(id1, pass1));
						else
							System.out.println("\nPassword not Match");								
					}else{
						System.out.println("\nInvalid OTP");
					}
					break;
				case 3:
					//Back to main menu.
					break;
				case 4:
					home=false;
					break;
				default:
					System.out.println("\n**Default Block(in Login Option)**\n\t\t<<Invalid Input>>\n\n");
				}
				break;
				/* ******************************** Case 1 ends here ******************************************** */
			case 2:

				System.out.println("\n**Register Here**");
				System.out.println("Name:\t");
				String name = sc.next();
				System.out.println("Email:\t");
				String email = sc.next();
				System.out.println("Password:");
				String pass1 = sc.next();
				System.out.println("Password Again:");
				String pass2 = sc.next();
				System.out.println("Contact:");
				String phoneNo = sc.next();
				System.out.println("User Type: "
						+ "\n\t1. Property Buyer"
						+ "\n\t2. Property Seller");
				String userType = sc.next();
				if(pass1.equals(pass2))
					System.out.println(obj.Register(new User(decode.decodeUser(userType) , email, pass1, name, phoneNo, null)));	//pass all parameters.
				break;
				/* ******************************** Case 2 ends here ******************************************** */
			case 3:
				System.out.println("\n**AboutUs**");
				break;
				/* ******************************** Case 3 ends here ******************************************** */
			case 4:
				System.out.println("\n**Feedback**");
				break;
				/* ******************************** Case 4 ends here ******************************************** */
			case 5:
				sc.close();
				System.out.println("Exit-ing");
				System.exit(1);
				/* ******************************** Case 5 ends here ******************************************** */
			default:
				System.out.println("\n**Default Block**\n\t\t<<++Invalid Input++>>\n\n");
			}
		}while(true);

	}

}
