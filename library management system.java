import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Main {

    // List to simulate the in-memory database for books and users
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();

    // List to track borrowed books
    private static ArrayList<BorrowedBook> borrowedBooks = new ArrayList<>();

    // User ID tracker
    private static int userIdCounter = 1;  // Starting User ID
    private static int bookIdCounter = 1;  // Starting Book ID

    // Main method to launch the GUI
    public static void main(String[] args) {
        // Create the main window (Frame)
        Frame mainFrame = new Frame("Library Management System");
        mainFrame.setLayout(new BorderLayout());

        // Set background color for the main frame
        mainFrame.setBackground(new Color(240, 248, 255)); // Alice Blue background for a soft look

        // Create a large title at the top
        Label titleLabel = new Label("LIBRARY MANAGEMENT SYSTEM", Label.CENTER);
        titleLabel.setFont(new Font("Courier", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 0, 139)); // Steel Blue color for the title
        mainFrame.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for the buttons
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(6, 2, 10, 10));  // 6 rows, 2 columns, with spacing between buttons
        buttonPanel.setBackground(Color.WHITE); // White Background for buttons panel

        // Create buttons for each operation
        Button addBookBtn = new Button("ADD BOOK");
        Button viewBooksBtn = new Button("VIEW BOOKS");
        Button borrowBookBtn = new Button("BORROW BOOK");
        Button returnBookBtn = new Button("RETURN BOOK");
        Button checkDueBtn = new Button("CHECK DUE");
        Button removeUserBtn = new Button("REMOVE USER");
        Button viewUsersBtn = new Button("VIEW USERS");
        Button addUserBtn = new Button("ADD USER");

        // Set button background color and foreground color with soft mild colors
        addBookBtn.setBackground(new Color(173, 216, 230));
        viewBooksBtn.setBackground(new Color(240, 230, 140));
        borrowBookBtn.setBackground(new Color(144, 238, 144));
        returnBookBtn.setBackground(new Color(255, 228, 181));
        checkDueBtn.setBackground(new Color(255, 239, 213));
        removeUserBtn.setBackground(new Color(255, 182, 193));
        viewUsersBtn.setBackground(new Color(221, 160, 221));
        addUserBtn.setBackground(new Color(216, 191, 216));

        // Set larger size for buttons and increased font size with Courier font
        Font largeFont = new Font("Courier", Font.BOLD, 16);

        addBookBtn.setFont(largeFont);
        viewBooksBtn.setFont(largeFont);
        borrowBookBtn.setFont(largeFont);
        returnBookBtn.setFont(largeFont);
        checkDueBtn.setFont(largeFont);
        removeUserBtn.setFont(largeFont);
        viewUsersBtn.setFont(largeFont);
        addUserBtn.setFont(largeFont);

        // Set button size
        addBookBtn.setPreferredSize(new Dimension(200, 50));
        viewBooksBtn.setPreferredSize(new Dimension(200, 50));
        borrowBookBtn.setPreferredSize(new Dimension(200, 50));
        returnBookBtn.setPreferredSize(new Dimension(200, 50));
        checkDueBtn.setPreferredSize(new Dimension(200, 50));
        removeUserBtn.setPreferredSize(new Dimension(200, 50));
        viewUsersBtn.setPreferredSize(new Dimension(200, 50));
        addUserBtn.setPreferredSize(new Dimension(200, 50));

        // Add action listeners to buttons
        addBookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();  // Trigger add book functionality
            }
        });

        viewBooksBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewBooks();  // View books
            }
        });

        borrowBookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrowBook();  // Borrow a book
            }
        });

        returnBookBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();  // Return a book
            }
        });

        checkDueBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkDue();  // Check due date
            }
        });

        removeUserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeUser();  // Remove a user
            }
        });

        viewUsersBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewUsers();  // View all users
            }
        });

        addUserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();  // Add a user
            }
        });

        // Add buttons to the panel
        buttonPanel.add(addBookBtn);
        buttonPanel.add(viewBooksBtn);
        buttonPanel.add(borrowBookBtn);
        buttonPanel.add(returnBookBtn);
        buttonPanel.add(checkDueBtn);
        buttonPanel.add(removeUserBtn);
        buttonPanel.add(viewUsersBtn);
        buttonPanel.add(addUserBtn);

        // Create a simple border effect with a background color
        Panel borderPanel = new Panel();
        borderPanel.setLayout(new BorderLayout());
        borderPanel.setBackground(Color.WHITE);
        borderPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the button panel to the main frame
        mainFrame.add(borderPanel, BorderLayout.CENTER);

        // Set frame properties
        mainFrame.setSize(500, 650); // Increased the size of the main frame to accommodate the new button
        mainFrame.setVisible(true);

        // Handle window close event
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);  // Close the application when the window is closed
            }
        });
    }

    // Add book functionality
    private static void addBook() {
        String title = JOptionPane.showInputDialog("Enter book title:");
        String author = JOptionPane.showInputDialog("Enter book author:");

        if (title != null && author != null && !title.trim().isEmpty() && !author.trim().isEmpty()) {
            Book newBook = new Book(bookIdCounter++, title, author);
            books.add(newBook);
            JOptionPane.showMessageDialog(null, "Book added: " + newBook.getTitle() + " by " + newBook.getAuthor());
        } else {
            JOptionPane.showMessageDialog(null, "Book title and author cannot be empty.");
        }
    }

    // View all books
    private static void viewBooks() {
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No books available.");
        } else {
            StringBuilder bookList = new StringBuilder();
            for (Book book : books) {
                bookList.append("ID: ").append(book.getId()).append(", Title: ").append(book.getTitle()).append(", Author: ").append(book.getAuthor()).append("\n");
            }
            JOptionPane.showMessageDialog(null, bookList.toString());
        }
    }

    // Borrow book functionality
    private static void borrowBook() {
        String userIdInput = JOptionPane.showInputDialog("Enter User ID:");
        if (userIdInput != null && !userIdInput.trim().isEmpty()) {
            int userId = Integer.parseInt(userIdInput.trim());
            User user = findUserById(userId);
            if (user != null) {
                String bookIdInput = JOptionPane.showInputDialog("Enter Book ID:");
                if (bookIdInput != null && !bookIdInput.trim().isEmpty()) {
                    int bookId = Integer.parseInt(bookIdInput.trim());
                    Book book = findBookById(bookId);
                    if (book != null) {
                        // Record the borrowed book and due date
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DAY_OF_YEAR, 14);  // 2 weeks due date
                        Date dueDate = cal.getTime();

                        BorrowedBook borrowedBook = new BorrowedBook(userId, book, dueDate);
                        borrowedBooks.add(borrowedBook);
                        JOptionPane.showMessageDialog(null, "THANKS FOR BORROWING. Due Date: " + dueDate);
                    } else {
                        JOptionPane.showMessageDialog(null, "Book not found.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }
        }
    }

    // Return borrowed book
    private static void returnBook() {
        String userIdInput = JOptionPane.showInputDialog("Enter User ID:");
        if (userIdInput != null && !userIdInput.trim().isEmpty()) {
            int userId = Integer.parseInt(userIdInput.trim());
            User user = findUserById(userId);
            if (user != null) {
                String bookIdInput = JOptionPane.showInputDialog("Enter Book ID:");
                if (bookIdInput != null && !bookIdInput.trim().isEmpty()) {
                    int bookId = Integer.parseInt(bookIdInput.trim());
                    BorrowedBook borrowedBook = findBorrowedBookById(userId, bookId);
                    if (borrowedBook != null) {
                        borrowedBooks.remove(borrowedBook);
                        JOptionPane.showMessageDialog(null, "Book returned successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "This book was not borrowed by the user.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }
        }
    }

    // Check due date for borrowed books
    private static void checkDue() {
        String userIdInput = JOptionPane.showInputDialog("Enter User ID:");
        if (userIdInput != null && !userIdInput.trim().isEmpty()) {
            int userId = Integer.parseInt(userIdInput.trim());
            User user = findUserById(userId);
            if (user != null) {
                StringBuilder dueList = new StringBuilder();
                for (BorrowedBook borrowedBook : borrowedBooks) {
                    if (borrowedBook.getUserId() == userId) {
                        dueList.append("Book: ").append(borrowedBook.getBook().getTitle())
                                .append(", Due Date: ").append(borrowedBook.getDueDate()).append("\n");
                    }
                }
                if (dueList.length() > 0) {
                    JOptionPane.showMessageDialog(null, dueList.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No borrowed books for this user.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }
        }
    }

    // Remove a user
    private static void removeUser() {
        String userIdInput = JOptionPane.showInputDialog("Enter User ID to remove:");
        if (userIdInput != null && !userIdInput.trim().isEmpty()) {
            int userId = Integer.parseInt(userIdInput.trim());
            User user = findUserById(userId);
            if (user != null) {
                users.remove(user);
                JOptionPane.showMessageDialog(null, "User removed: " + user.getUserName());
            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }
        }
    }

    // View all users
    private static void viewUsers() {
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No users available.");
        } else {
            StringBuilder userList = new StringBuilder();
            for (User user : users) {
                userList.append("ID: ").append(user.getUserId()).append(", Name: ").append(user.getUserName()).append("\n");
            }
            JOptionPane.showMessageDialog(null, userList.toString());
        }
    }

    // Add a new user and display the ID
    private static void addUser() {
        String userName = JOptionPane.showInputDialog("Enter user name:");
        if (userName != null && !userName.trim().isEmpty()) {
            User newUser = new User(userIdCounter++, userName);
            users.add(newUser);
            JOptionPane.showMessageDialog(null, "User added: " + newUser.getUserName() + "\nUser ID: " + newUser.getUserId());
        } else {
            JOptionPane.showMessageDialog(null, "User name cannot be empty.");
        }
    }

    // Utility methods to find books and users by their IDs
    private static Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    private static User findUserById(int id) {
        for (User user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    private static BorrowedBook findBorrowedBookById(int userId, int bookId) {
        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (borrowedBook.getUserId() == userId && borrowedBook.getBook().getId() == bookId) {
                return borrowedBook;
            }
        }
        return null;
    }
}

// Book class
class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

// User class
class User {
    private int userId;
    private String userName;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}

// BorrowedBook class to track borrowed books
class BorrowedBook {
    private int userId;
    private Book book;
    private Date dueDate;

    public BorrowedBook(int userId, Book book, Date dueDate) {
        this.userId = userId;
        this.book = book;
        this.dueDate = dueDate;
    }

    public int getUserId() {
        return userId;
    }

    public Book getBook() {
        return book;
    }

    public Date getDueDate() {
        return dueDate;
    }
}