//package com.project.petmanagement.adapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.project.petmanagement.R;
//import com.project.petmanagement.activity.shop.OrderDetailActivity;
//import com.project.petmanagement.utils.FormatNumberUtils;
//
//import java.util.List;
//
//public class ListOrderAdapter extends RecyclerView.Adapter<ListOrderAdapter.OrderViewHolder>{
//    private Context context;
//    private List<Order> orderList;
//
//    public ListOrderAdapter(Context context, List<Order> orderList) {
//        this.context = context;
//        this.orderList = orderList;
//    }
//
//    @NonNull
//    @Override
//    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new OrderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
//        Order order = orderList.get(position);
//        holder.idOrder.setText(String.valueOf(order.getId()));
//        holder.quantity.setText(String.valueOf(order.getOrderDetais().size()));
//        holder.totalPrice.setText(FormatNumberUtils.formatFloat(order.getTotalPrice()));
//        holder.status.setText(order.getStatus());
//        ListImageOrderDetailAdapter imageOrderDetailAdapter = new ListImageOrderDetailAdapter(context,order.getOrderDetais());
//        holder.imageRecyclerView.setAdapter(imageOrderDetailAdapter);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//        holder.imageRecyclerView.setLayoutManager(layoutManager);
//        holder.btnOrderDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, OrderDetailActivity.class);
//                context.startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        if(orderList!=null)
//            return  orderList.size();
//        return 0;
//    }
//
//    public static class OrderViewHolder extends RecyclerView.ViewHolder{
//        private final TextView idOrder, dateOrder,quantity, totalPrice, status;
//        private final RecyclerView imageRecyclerView;
//        private final Button btnOrderDetail;
//        public OrderViewHolder(@NonNull View itemView) {
//            super(itemView);
//            idOrder = itemView.findViewById(R.id.id_order);
//            dateOrder = itemView.findViewById(R.id.date_order);
//            quantity = itemView.findViewById(R.id.quantity);
//            totalPrice = itemView.findViewById(R.id.total_price);
//            status = itemView.findViewById(R.id.status);
//            imageRecyclerView = itemView.findViewById(R.id.image_product_recyclerview);
//            btnOrderDetail = itemView.findViewById(R.id.btn_order_detail);
//        }
//    }
//}
