package com.dd.medication.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

/**
 * 手机唯一标识码
 * 
 * @return
 */
public class PhoneUUID {

	/**获取手机的唯一标识 这里为多个标识的合*/
	public static String getPhoneWYBS(Context context) {
		/** Pseudo-Unique ID, 这个在任何Android手机中都有效 **/
		String m_szDevIDShort = "35"
				+ // we make this look like a valid IMEI
				Build.BOARD.length() % 10 + Build.BRAND.length() % 10
				+ Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
				+ Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
				+ Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
				+ Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
				+ Build.TAGS.length() % 10 + Build.TYPE.length() % 10
				+ Build.USER.length() % 10; // 13 digits

		/** The Android ID */
		String m_szAndroidID = Secure.getString(context.getContentResolver(),
				Secure.ANDROID_ID);

		/**
		 * The WLAN MAC Address string
		 * 是另一个唯一ID。但是你需要为你的工程加入android.permission.ACCESS_WIFI_STATE
		 * 权限，否则这个地址会为null。
		 */
		WifiManager wm = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();

		/**
		 * The BT MAC Address
		 * string只在有蓝牙的设备上运行。并且要加入android.permission.BLUETOOTH 权限.
		 **/
		BluetoothAdapter m_BluetoothAdapter = null;
		// Local Bluetooth adapter
		m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		String m_szBTMAC = m_BluetoothAdapter.getAddress();

		// 作为手机来讲，IMEI是唯一的 需要权限 android.permission.READ_PHONE_STATE
		TelephonyManager TelephonyMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String m_szImei = TelephonyMgr.getDeviceId();

		String m_szLongID = m_szImei + m_szDevIDShort + m_szAndroidID
				+ m_szWLANMAC + m_szBTMAC;
		// compute md5
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
		// get md5 bytes
		byte p_md5Data[] = m.digest();
		// create a hex string
		String m_szUniqueID = new String();
		for (int i = 0; i < p_md5Data.length; i++) {
			int b = (0xFF & p_md5Data[i]);
			// if it is a single digit, make sure it have 0 in front (proper
			// padding)
			if (b <= 0xF)
				m_szUniqueID += "0";
			// add number to string
			m_szUniqueID += Integer.toHexString(b);
		} // hex string to uppercase
		return m_szUniqueID = m_szUniqueID.toUpperCase();
	}

}
