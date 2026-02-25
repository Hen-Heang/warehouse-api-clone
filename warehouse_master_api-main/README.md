# Stock flow Commerce API

**Build with** Spring boot

## PROJECT ADDRESS
https://spring.hanyeaktong.site/swagger-ui/index.html


## Installation


****To Get started:****

1. Clone repo
    ```bash
    git clone https://github.com/ksga-11th-generation-basic-course/warehouse_master_api.git
    ```

2. To run the code
   - Start the project Go to file > Open > Choose the folder of the cloned project
   - Run the project by clicking the start button
   - Open browser and type http://localhost:8888/swagger-ui/index.html

> **Stock flow Commerce** is a B2B trading platform for business between Distributor and Retailer. Business owners can trade more efficiently with lower operational complexity.

### What we have done

- **API authorization** (/authorization)
  http://localhost:8888/authorization
  - Sign up : register an account.
  - Log in : log in your account if verified
  - Change password: Change to new password
  - Forget Password: By combining OTP endpoint, we can request new password if we forget our password
- **Generate OTP** (/authorization/api/v1/otp/)
  http://localhost:8888/authorization/api/v1/otp/
  - Generate OTP : generate a new 4 digits OTP code and send it to your email.
  - Veryfy OTP : Verify your account

**There are 2 types of user in the system**

*Distributor*

- **Distributor profile controller** (/api/v1/distributor/profiles/)
  http://localhost:8888/api/v1/distributor/profiles
  - Add user profile : create a user profile for distributor.
  - Get user profile : get details of user profile
  - Update user profile : update distributor user profile
- **Distributor Store Controller** (/api/v1/distributor/stores/)
  http://localhost:8888/api/v1/distributor/stores
  - Setup new store : Setup new store for distributor
  - Get store detail : view detail of store
  - Edit store : update store detail
  - Disable store : delist store from public view
  - Enable store : list store back to view back
  - Delete store : remove store completely
- **Distributor Category Controller** (/api/v1/distributor/categories/)
  http://localhost:8888/api/v1/distributor/categories
  - Create new category : create new category for the store
  - Get category by id : get category detail using the id
  - Get all category : get every category in the store
  - Search category by name : filter category by searching the name
  - Edit category : update category info
  - Delete category : remove category from store
- **Distributor Product Controller** (/api/v1/distributor/products/)
  http://localhost:8888/api/v1/distributor/products
  - Create new product : Create new product for the store
  - Import product : restock product and set new price for the product
  - Get all product :  get product listing with sorting. can sort by created_date, qty, name, price, and product_id
  - Get product by id : get product detail by id
  - Edit product : update product detail
  - Unpublish product : delist product from public
  - Publish product : list product in public
  - Delete product : remove product permanently
- **Distributor Order Controller** (/api/v1/distributor/orders/)
  http://localhost:8888/api/v1/distributor/orders
  - Get all order : get all orders listing
  - Get pending order : get all orders that is pending
  - Get preparing order : get all orders that is preparing
  - Get dispatching order : get all orders that is dispatching
  - Get confirming order : get all orders that is comfirming
  - Get completed order : get all orders that has been completed
  - Get order detail : get detail of an order
  - Get invoice : get invoice of the store. order need to be completed in other to get invoice
  - Accept order : accept order that is still in pending. update to preparing
  - Decline order : decline order that is in pending
  - Update order to dispatching : update order status that is preparing to dispatching
  - Update order to confirming : update order status that is dispatching to confirming. confirm order will wait for retailer to comfirm that they got the product/order
- **Distributor Homepage Controller** (/api/v1/distributor/order_activities/)
  http://localhost:8888/api/v1/distributor/order_activities
  - Get all order : get all orders listing
- **Distributor Report Controller** (/api/v1/distributor/reports/)
  http://localhost:8888/api/v1/distributor/reports
  - Get order activity homepage by month : get order activity by monthly basis
  - Get order activity homepage by year : get order activity by yearly basis
- **Distributor History Controller** (/api/v1/distributor/history/)
  http://localhost:8888/api/v1/distributor/history
  - Get history order : get history of order. have pagination
  - Get history import : get history of import or restock

*Retailer*

- **Retailer Store Controller** (/api/v1/retailer/stores/)
  http://localhost:8888/api/v1/retailer/stores
  - Rate store : give rating to a store
  - Bookmark store : bookmark a store. set store as favorite
  - Edit rating : change rating of a store
  - Delete rating : remove rating from a store
  - Remove bookmark : unbookmark a store
  - Get store rating : Get rating of a store by store id
  - Get store product : get all products from store
  - Get store by id : fetch store by its id
  - Get store order by rating : get store filter by store rating
  - Get store order by name : get store filter by name
  - Get store order by favorite : get store filter by favorite. can be first or last
  - Get store order by date : get store filter by created date
  - Get store by bookmark : get only boomarked/favorite store
  - Search store by name : find store by searching store name
  - Get all store : get store without filter
- **Retailer Profile Controller** (/api/v1/retailer/profiles/)
  http://localhost:8888/api/v1/retailer/profiles
  - Create retailer profile : create a profile for retailer. if not created, can not use all feature
  - Get profile : Get profile details
  - Edit profile : Change or update profile
- **Retailer Profile Controller** (/api/v1/retailer/orders/)
  http://localhost:8888/api/v1/retailer/orders
  - Add to cart : add product to cart. can be one product or a list
  - Update cart product : update product in the cart (qty)
  - Remove product : remove product from cart
  - Confirm order : confirm cart as order
  - Draft cart : save cart as draft for continuing later
  - Cancel order : delete cart and cancel order
  - Confirm transaction : when product is delivered retailer have to confirm that they receive the product
  - Get cart details : get details of cart
  - Get order details : get details of the order by id
  - Get orders progress : get all orders and its progress
  - Get invoice : get invoice details
- **Retailer Report Controller** (/api/v1/retailer/reports/)
  http://localhost:8888/api/v1/retailer/report
  - Get report : get retailer report
- **Retailer History Controller** (/api/v1/retailer/history/)
  http://localhost:8888/api/v1/retailer/history
  - Get order history : get history of order
  - Get draft : get saved draft
  
## Testing

### Database Server
- Database : warehouse_master
- Username : warehouse_master
- Password : warehouse_master
- Port : 5436
- Ip / Host : 8.219.139.67

### Connection Database
Go to **DataGrip** and choose the connection then input the information as provided (Database server).

**Note**
- This system have 2 roles which is distributor and retailer.
- This system need real verifiable email address with OTP
- Role of distributor is 1 while retailer is 2
