import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Product> productList;

    public static void main(String[] args) {

        productList = readFile();

        // Thêm mới sản phẩm:
        System.out.println("Nhập số lượng sản phẩm muốn thêm vào?");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Product product = addProduct();
            productList.add(product);
        }
        writeToFile();

        // Hiển thị danh sách sản phẩm:
        showProduct();

        // TÌm kiếm sản phẩm
        System.out.println("Nhập sản phẩm bạn muốn tìm?");
        String searchProduct = scanner.nextLine();
        searchProduct(searchProduct);
    }

    public static Product addProduct() {
        Product product = new Product();
        System.out.println("Nhập id: ");
        product.setId(Integer.parseInt(scanner.nextLine()));
        System.out.println("Nhập tên sản phẩm: ");
        product.setName(scanner.nextLine());
        System.out.println("Nhập tên hãng sản phẩm: ");
        product.setManufacturer(scanner.nextLine());
        System.out.println("Nhập giá sản phảm: ");
        product.setPrice(scanner.nextDouble());
        System.out.println("Nhập thông tin khác: ");
        product.setStatus(scanner.nextLine());
        return product;
    }

    public static void showProduct() {
        for (Product product : productList
        ) {
            System.out.println(product);
        }
    }

    public static void searchProduct(String name) {
        boolean check = false;
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(name)) {
                check = true;
                System.out.println(product);
            }
        }
        if (!check){
            System.out.println("Không tìm thấy sản phẩm nào?");
        }
    }
    public static List<Product> readFile(){
        try {
            File file = new File("product.txt");
            if (file.exists()){
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream((fis));
                List<Product> productStream = (List<Product>) ois.readObject();
                ois.close();
                fis.close();
                return productStream;
            }
            return new ArrayList<>();
        }catch (IOException | ClassNotFoundException i){
            i.printStackTrace();
        }
        return new ArrayList<>();
    }
    public static void writeToFile(){
        try {
            File file = new File("product.txt");
            if (!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(productList);
            oos.close();
            fos.close();

        }catch (IOException i){
            i.printStackTrace();
        }
    }
}