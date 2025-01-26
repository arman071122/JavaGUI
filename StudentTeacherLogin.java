package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class StudentTeacherLogin {
	static Scanner sc = new Scanner(System.in);

	static void signUp(Connection c) throws SQLException {
		int Tclass = 0;
		System.out.println("\n=====================");
		System.out.println("****** SIGN UP ******");
		System.out.println("=====================");

		try {
			PreparedStatement signUp = c
					.prepareStatement("INSERT INTO teacher(Tname, Class_In_Charge, password) VALUES (?, ?, ?)");

			System.out.println("\nEnter ");
			System.out.print("NAME            : ");
			String Tname = sc.next();
			System.out.print("CLASS IN CHARGE : ");
			Tclass = sc.nextInt();
			System.out.print("PASSWORD        : ");
			String Tpass = sc.next();

			System.out.println("\nConfirm the details:");
			System.out.println("\nNAME            : " + Tname);
			System.out.println("CLASS IN CHARGE : " + Tclass);
			System.out.println("PASSWORD        : " + Tpass);
			System.out.print("\nProceed with registration? (y/n): ");
			char confirm = sc.next().charAt(0);

			if (confirm == 'y') {
				signUp.setString(1, Tname);
				signUp.setInt(2, Tclass);
				signUp.setString(3, Tpass);
				int SignUpRes = signUp.executeUpdate();

				if (SignUpRes > 0)
					System.out.println("\nTeacher Account Created Successfully");
			} else {
				System.out.println("\nRegistration canceled.");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("\nClass In_charge for class " + Tclass + " already exists");
		}
	}

	static void login(Connection c) throws SQLException {
		boolean loggedIn = false;

		while (!loggedIn) {
			System.out.println("\n===================");
			System.out.println("****** LOGIN ******");
			System.out.println("===================");

			System.out.println();
			System.out.print("NAME     : ");
			String name = sc.next();
			System.out.print("PASSWORD : ");
			String pass = sc.next();

			PreparedStatement Slogin = c.prepareStatement("select * from student where sname=? and password=?");
			Slogin.setString(1, name);
			Slogin.setString(2, pass);

			PreparedStatement Tlogin = c.prepareStatement("select * from teacher where tname=? and password=?");
			Tlogin.setString(1, name);
			Tlogin.setString(2, pass);

			ResultSet Sres = Slogin.executeQuery();
			ResultSet Tres = Tlogin.executeQuery();

			if (Sres.next() && pass.equals(Sres.getString("password"))) {
				loggedIn = true;
				boolean logout = false;
				int SidLogin = Sres.getInt("Sid");
				int ch;
				Student student = new Student();

				do {
					System.out.println("\n======================================");
					System.out.println("       Welcome student, " + name);
					System.out.println("======================================");
					System.out.println("\n1. PERSONAL INFO");
					System.out.println("2. VIEW MARK");
					System.out.println("3. CHANGE PASSWORD");
					System.out.println("4. LOGOUT");

					System.out.print("\nEnter choice : ");
					ch = sc.nextInt();

					switch (ch) {
					case 1: {
						student.PersonalInfo(c, name);
						break;
					}
					case 2: {
						student.ViewMark(c, SidLogin);
						break;
					}
					case 3: {
						logout = student.ChangePass(c, sc, SidLogin, name, logout);
						break;
					}
					case 4: {
						System.out.println("\nLogging out of " + name + "....");
						logout = true;
						break;
					}
					default:
						System.out.println("Invalid input");
					}
				} while (ch != 4 && !logout);
			} else if (Tres.next() && pass.equals(Tres.getString("password"))) {
				loggedIn = true;
				boolean logout = false;
				int ch;
				Teacher teacher = new Teacher();

				do {
					System.out.println("\n======================================");
					System.out.println("       Welcome teacher, " + name);
					System.out.println("======================================");
					System.out.println("\n1. ADD STUDENT");
					System.out.println("2. ADD MARKS");
					System.out.println("3. VIEW CLASS");
					System.out.println("4. VIEW STUDENT");
					System.out.println("5. DELETE STUDENT");
					System.out.println("6. MODIFY STUDENT");
					System.out.println("7. PERSONAL INFO");
					System.out.println("8. EDIT PERSONAL INFO");
					System.out.println("9. LOGOUT");

					PreparedStatement s = c.prepareStatement("select Class_In_Charge,Tid from teacher where tname=?");
					s.setString(1, name);
					ResultSet getClass = s.executeQuery();

					getClass.next();
					int classIn = getClass.getInt(1);
					int Tid = getClass.getInt(2);

					System.out.print("\nEnter choice : ");
					ch = sc.nextInt();

					switch (ch) {
					case 1: {
						char addStu = 'y';
						while (addStu == 'y') {
							System.out.println("\n==========================");
							System.out.println("******* ADD STUDENT ******");
							System.out.println("==========================");

							System.out.println("\nEnter student's");
							System.out.print("NAME     : ");
							String Sname = sc.next();

							int Sid = teacher.AddStu(c, classIn, sc, Sname);

							System.out.print("\nAdd marks to the student " + Sname + " now? (y/n): ");
							char addMark = sc.next().charAt(0);

							if (addMark == 'y') {
								teacher.AddMark(c, classIn, sc, Sid);
							}

							System.out.print("\nDo you want to add another student? (y/n): ");
							addStu = sc.next().charAt(0);
						}

						break;
					}

					case 2: {
						char addMark = 'y';
						while (addMark == 'y') {
							System.out.println("\n========================");
							System.out.println("******* ADD MARKS ******");
							System.out.println("========================");

							System.out.print("\nEnter ID of student for entering marks: ");
							int Sid = sc.nextInt();

							teacher.AddMark(c, classIn, sc, Sid);
							System.out.print("\nDo you want to add marks for another student? (y/n): ");
							addMark = sc.next().charAt(0);
						}
						break;
					}

					case 3: {
						System.out.println("\n======================");
						System.out.println("******* CLASS " + classIn + " ******");
						System.out.println("======================");

						teacher.ViewClass(c, classIn);
						break;
					}

					case 4: {
						char viewAnother = 'y';
						while (viewAnother == 'y') {
							System.out.println("\n=================================");
							System.out.println("******* VIEW STUDENT BY ID ******");
							System.out.println("=================================");

							teacher.ViewStu(c, classIn, sc);
							System.out.print("\nDo you want to view another student? (y/n): ");
							viewAnother = sc.next().charAt(0);
						}

						break;
					}

					case 5: {
						char remAgain = 'y';
						while (remAgain == 'y') {
							System.out.println("\n=================================");
							System.out.println("*******   REMOVE STUDENT   ******");
							System.out.println("=================================");

							teacher.RemoveStu(c, classIn, sc);
							System.out.print("\nDo you want to delete another student? (y/n): ");
							remAgain = sc.next().charAt(0);
						}

						break;
					}

					case 6: {
						char modAgain = 'y';
						while (modAgain == 'y') {
							System.out.println("\n=================================");
							System.out.println("*******   MODIFY STUDENT   ******");
							System.out.println("=================================");

							teacher.ModifyStu(c, classIn, sc);
							System.out.print("\nDo you want to modify another student? (y/n): ");
							modAgain = sc.next().charAt(0);
						}

						break;
					}

					case 7: {
						System.out.println("\n============================");
						System.out.println("******* PERSONAL INFO ******");
						System.out.println("============================");

						teacher.PersonalInfo(c, Tid, sc, Tid);
						break;
					}

					case 8: {
						System.out.println("\n=================================");
						System.out.println("******* EDIT PERSONAL INFO ******");
						System.out.println("=================================");

						logout = teacher.Edit(c, classIn, sc, logout, Tid);
						break;
					}

					case 9: {
						System.out.println("\nLogging out of " + name + "....");
						logout = true;
						break;
					}
					default: {
						System.out.println("\nInvalid Input");
					}
					}
				} while (ch != 9 && !logout);
			} else {
				System.out.println("\nInvalid credentials. Please try again.");
			}
		}
	}

	public static void main(String[] args) throws SQLException {

		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "arman", "arman@07");

		int ch;
		do {
			System.out.println("\n======================================");
			System.out.println("****** SCHOOL MANAGEMENT SYSTEM ******");
			System.out.println("======================================");
			System.out.println("\n1. CREATE ACCOUNT (ONLY TEACHER)");
			System.out.println("2. LOGIN");
			System.out.println("3. EXIT");
			System.out.print("\nEnter choice : ");
			ch = sc.nextInt();

			switch (ch) {
			case 1: {
				signUp(c);
				break;
			}
			case 2: {
				login(c);
				break;
			}
			case 3: {
				System.out.println("\nExiting Application....");
				break;
			}
			default: {
				System.out.println("\nInvalid Input");
			}
			}

		} while (ch != 3);
	}
}