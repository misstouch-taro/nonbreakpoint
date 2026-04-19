# NonBreakpoint

---

## 🇺🇸 English

An Eclipse plugin that automatically skips marked lines during step debugging.

### Overview

NonBreakpoint lets you mark specific lines in your Java source code so that the debugger automatically steps over them during step execution. Unlike regular breakpoints that stop execution, non-breakpoints are silently skipped — keeping your debug session focused on the code that matters.

### Features

- **Toggle non-breakpoints** via right-click menu or keyboard shortcut
- **Multi-line support** — select multiple lines and toggle them all at once
- **Persistent markers** — non-breakpoints are saved and restored after Eclipse restarts
- **Conflict prevention** — a line cannot have both a breakpoint and a non-breakpoint at the same time
- **Visual indicators** — marked lines are shown in the left vertical ruler and overview ruler

### Requirements

- Eclipse IDE (2023-03 or later recommended)
- Java SE 21 or later

### Installation

1. Download the latest `.jar` file from [Releases](https://github.com/misstouch-taro/nonbreakpoint/releases)
2. Place the `.jar` file into the `dropins` folder of your Eclipse installation
3. Restart Eclipse

### Usage

1. Place your cursor on the line you want to mark (or select multiple lines)
2. Press `Ctrl + Shift + N` — or right-click and select **Toggle Non-Breakpoint**
3. Press `Ctrl + Shift + N` again on a marked line to remove it

### Notes

- Non-breakpoints only take effect during **step execution** (Step Over, Step Into).
- A line cannot have both a breakpoint and a non-breakpoint at the same time.
- Adding a breakpoint to a non-breakpoint line will automatically remove the non-breakpoint.

---

## 🇯🇵 日本語

ステップデバッグ中にマークした行を自動でスキップする Eclipse プラグインです。

### 概要

NonBreakpoint を使うと、Java ソースコードの特定の行にマークを付け、ステップ実行中にデバッガーがその行を自動でスキップするようになります。通常のブレークポイントとは異なり、ノンブレークポイントは停止せずに通過するため、デバッグセッションを本当に必要な箇所に集中させることができます。

### 機能

- 右クリックメニューまたはキーボードショートカットで切替
- 複数行選択で一括設定
- Eclipse 再起動後もマークが保持される
- ブレークポイントとの競合を自動防止
- 左ルーラーおよびオーバービュールーラーにアイコン表示

### 動作環境

- Eclipse IDE (2023-03 以降推奨)
- Java SE 21 以降

### インストール

1. [Releases](https://github.com/misstouch-taro/nonbreakpoint/releases) から最新の `.jar` ファイルをダウンロード
2. Eclipse インストールフォルダ内の `dropins` フォルダに配置
3. Eclipse を再起動

### 使い方

1. マークしたい行にカーソルを置く（複数行選択も可）
2. `Ctrl + Shift + N` を押す、または右クリックで **Toggle Non-Breakpoint** を選択
3. マーク済みの行で再度 `Ctrl + Shift + N` を押すと解除

---

## 🇨🇳 简体中文

一款在单步调试过程中自动跳过已标记行的 Eclipse 插件。

### 概述

NonBreakpoint 允许您在 Java 源代码中标记特定行，使调试器在单步执行时自动跳过这些行。与普通断点不同，非断点行会被静默跳过，让您的调试专注于真正重要的代码。

### 功能

- 通过右键菜单或键盘快捷键切换非断点
- 支持多行选择，一次性批量设置
- Eclipse 重启后标记依然保留
- 自动防止与普通断点冲突
- 在左侧标尺和概览标尺中显示图标

### 系统要求

- Eclipse IDE（推荐 2023-03 或更高版本）
- Java SE 21 或更高版本

### 安装

1. 从 [Releases](https://github.com/misstouch-taro/nonbreakpoint/releases) 下载最新 `.jar` 文件
2. 将 `.jar` 文件放入 Eclipse 安装目录下的 `dropins` 文件夹
3. 重启 Eclipse

### 使用方法

1. 将光标放在要标记的行（也可选择多行）
2. 按 `Ctrl + Shift + N`，或右键选择 **Toggle Non-Breakpoint**
3. 在已标记行再次按 `Ctrl + Shift + N` 可取消标记

---

## 🇰🇷 한국어

단계별 디버깅 중 표시된 줄을 자동으로 건너뛰는 Eclipse 플러그인입니다.

### 개요

NonBreakpoint를 사용하면 Java 소스 코드의 특정 줄에 표시를 설정하여, 디버거가 단계 실행 중 해당 줄을 자동으로 건너뛰도록 할 수 있습니다. 일반 중단점과 달리 논브레이크포인트는 조용히 통과되어 디버그 세션을 중요한 코드에 집중시킵니다.

### 기능

- 우클릭 메뉴 또는 키보드 단축키로 전환
- 여러 줄 선택 후 일괄 설정 가능
- Eclipse 재시작 후에도 표시 유지
- 일반 중단점과의 충돌 자동 방지
- 왼쪽 눈금자 및 개요 눈금자에 아이콘 표시

### 요구 사항

- Eclipse IDE (2023-03 이상 권장)
- Java SE 21 이상

### 설치

1. [Releases](https://github.com/misstouch-taro/nonbreakpoint/releases)에서 최신 `.jar` 파일 다운로드
2. Eclipse 설치 폴더의 `dropins` 폴더에 배치
3. Eclipse 재시작

### 사용 방법

1. 표시할 줄에 커서를 놓기 (여러 줄 선택 가능)
2. `Ctrl + Shift + N` 누르기 또는 우클릭 후 **Toggle Non-Breakpoint** 선택
3. 표시된 줄에서 다시 `Ctrl + Shift + N`을 누르면 해제

---

## 🇩🇪 Deutsch

Ein Eclipse-Plugin, das markierte Zeilen beim Schritt-Debugging automatisch überspringt.

### Übersicht

Mit NonBreakpoint können Sie bestimmte Zeilen in Ihrem Java-Quellcode markieren, sodass der Debugger diese Zeilen beim Schritt-Debugging automatisch überspringt. Im Gegensatz zu regulären Breakpoints werden Non-Breakpoints lautlos übersprungen – damit Sie sich beim Debuggen auf den wichtigen Code konzentrieren können.

### Funktionen

- Umschalten über Rechtsklick-Menü oder Tastenkürzel
- Mehrere Zeilen gleichzeitig markieren
- Markierungen bleiben nach Eclipse-Neustart erhalten
- Automatische Konfliktvermeidung mit regulären Breakpoints
- Anzeige im linken Lineal und im Übersichtslineal

### Voraussetzungen

- Eclipse IDE (2023-03 oder neuer empfohlen)
- Java SE 21 oder neuer

### Installation

1. Neueste `.jar`-Datei von [Releases](https://github.com/misstouch-taro/nonbreakpoint/releases) herunterladen
2. `.jar`-Datei in den `dropins`-Ordner Ihrer Eclipse-Installation legen
3. Eclipse neu starten

### Verwendung

1. Cursor auf die gewünschte Zeile setzen (oder mehrere Zeilen auswählen)
2. `Ctrl + Shift + N` drücken oder Rechtsklick → **Toggle Non-Breakpoint**
3. Erneut `Ctrl + Shift + N` auf einer markierten Zeile drücken, um die Markierung zu entfernen

---

## 🇫🇷 Français

Un plugin Eclipse qui saute automatiquement les lignes marquées lors du débogage pas à pas.

### Présentation

NonBreakpoint vous permet de marquer des lignes spécifiques dans votre code source Java afin que le débogueur les ignore automatiquement lors de l'exécution pas à pas. Contrairement aux points d'arrêt classiques, les non-points d'arrêt sont ignorés silencieusement, ce qui vous permet de vous concentrer sur le code important.

### Fonctionnalités

- Activation via le menu contextuel ou un raccourci clavier
- Sélection de plusieurs lignes pour un marquage groupé
- Les marqueurs sont conservés après le redémarrage d'Eclipse
- Prévention automatique des conflits avec les points d'arrêt
- Affichage dans la règle verticale gauche et la règle de vue d'ensemble

### Configuration requise

- Eclipse IDE (2023-03 ou version ultérieure recommandée)
- Java SE 21 ou version ultérieure

### Installation

1. Téléchargez le dernier fichier `.jar` depuis [Releases](https://github.com/misstouch-taro/nonbreakpoint/releases)
2. Placez le fichier `.jar` dans le dossier `dropins` de votre installation Eclipse
3. Redémarrez Eclipse

### Utilisation

1. Placez le curseur sur la ligne à marquer (ou sélectionnez plusieurs lignes)
2. Appuyez sur `Ctrl + Shift + N` ou faites un clic droit → **Toggle Non-Breakpoint**
3. Appuyez à nouveau sur `Ctrl + Shift + N` sur une ligne marquée pour la démarquer

---

## 🇪🇸 Español

Un plugin de Eclipse que omite automáticamente las líneas marcadas durante la depuración paso a paso.

### Descripción

NonBreakpoint le permite marcar líneas específicas en su código fuente Java para que el depurador las omita automáticamente durante la ejecución paso a paso. A diferencia de los puntos de interrupción normales, los no-breakpoints se saltan silenciosamente, manteniendo su sesión de depuración enfocada en el código importante.

### Características

- Alternar mediante el menú contextual o atajo de teclado
- Soporte para selección múltiple de líneas
- Los marcadores se conservan tras reiniciar Eclipse
- Prevención automática de conflictos con puntos de interrupción
- Indicadores visuales en el margen izquierdo y el panel de vista general

### Requisitos

- Eclipse IDE (se recomienda 2023-03 o posterior)
- Java SE 21 o posterior

### Instalación

1. Descargue el último archivo `.jar` desde [Releases](https://github.com/misstouch-taro/nonbreakpoint/releases)
2. Coloque el archivo `.jar` en la carpeta `dropins` de su instalación de Eclipse
3. Reinicie Eclipse

### Uso

1. Coloque el cursor en la línea que desea marcar (o seleccione varias líneas)
2. Presione `Ctrl + Shift + N` o haga clic derecho → **Toggle Non-Breakpoint**
3. Presione `Ctrl + Shift + N` nuevamente en una línea marcada para desmarcarla

---

## 🇵🇹 Português

Um plugin do Eclipse que pula automaticamente as linhas marcadas durante a depuração passo a passo.

### Visão Geral

O NonBreakpoint permite marcar linhas específicas no seu código-fonte Java para que o depurador as ignore automaticamente durante a execução passo a passo. Ao contrário dos pontos de interrupção comuns, os non-breakpoints são ignorados silenciosamente, mantendo sua sessão de depuração focada no código que importa.

### Funcionalidades

- Alternar via menu de contexto ou atalho de teclado
- Suporte a seleção de múltiplas linhas
- Os marcadores são mantidos após reiniciar o Eclipse
- Prevenção automática de conflitos com breakpoints comuns
- Indicadores visuais na régua vertical esquerda e na régua de visão geral

### Requisitos

- Eclipse IDE (recomendado 2023-03 ou posterior)
- Java SE 21 ou posterior

### Instalação

1. Baixe o arquivo `.jar` mais recente em [Releases](https://github.com/misstouch-taro/nonbreakpoint/releases)
2. Coloque o arquivo `.jar` na pasta `dropins` da sua instalação do Eclipse
3. Reinicie o Eclipse

### Uso

1. Posicione o cursor na linha que deseja marcar (ou selecione várias linhas)
2. Pressione `Ctrl + Shift + N` ou clique com o botão direito → **Toggle Non-Breakpoint**
3. Pressione `Ctrl + Shift + N` novamente em uma linha marcada para desmarcá-la

---

## 🇷🇺 Русский

Плагин Eclipse, который автоматически пропускает отмеченные строки при пошаговой отладке.

### Обзор

NonBreakpoint позволяет отмечать определённые строки в исходном коде Java, чтобы отладчик автоматически пропускал их при пошаговом выполнении. В отличие от обычных точек останова, non-breakpoints пропускаются без остановки — позволяя сосредоточиться на нужных участках кода.

### Возможности

- Переключение через контекстное меню или сочетание клавиш
- Поддержка выделения нескольких строк
- Отметки сохраняются после перезапуска Eclipse
- Автоматическое предотвращение конфликтов с точками останова
- Отображение значков на левой линейке и линейке обзора

### Требования

- Eclipse IDE (рекомендуется 2023-03 или новее)
- Java SE 21 или новее

### Установка

1. Скачайте последний файл `.jar` со страницы [Releases](https://github.com/misstouch-taro/nonbreakpoint/releases)
2. Поместите файл `.jar` в папку `dropins` вашей установки Eclipse
3. Перезапустите Eclipse

### Использование

1. Установите курсор на нужную строку (или выделите несколько строк)
2. Нажмите `Ctrl + Shift + N` или щёлкните правой кнопкой мыши → **Toggle Non-Breakpoint**
3. Нажмите `Ctrl + Shift + N` ещё раз на отмеченной строке, чтобы снять отметку

---

## 🇮🇳 हिन्दी

एक Eclipse प्लगइन जो स्टेप डीबगिंग के दौरान चिह्नित पंक्तियों को स्वचालित रूप से छोड़ता है।

### अवलोकन

NonBreakpoint आपको Java सोर्स कोड की विशेष पंक्तियों को चिह्नित करने देता है, जिससे डीबगर स्टेप एक्जीक्यूशन के दौरान उन पंक्तियों को स्वचालित रूप से छोड़ देता है। सामान्य ब्रेकपॉइंट के विपरीत, नॉन-ब्रेकपॉइंट चुपचाप छोड़ दिए जाते हैं — जिससे आप महत्वपूर्ण कोड पर ध्यान केंद्रित कर सकते हैं।

### विशेषताएँ

- राइट-क्लिक मेनू या कीबोर्ड शॉर्टकट से टॉगल करें
- एक साथ कई पंक्तियाँ चुनकर सेट करें
- Eclipse पुनः आरंभ के बाद भी चिह्न बने रहते हैं
- सामान्य ब्रेकपॉइंट के साथ टकराव स्वचालित रूप से रोका जाता है
- बाईं रूलर और ओवरव्यू रूलर में आइकन प्रदर्शित होता है

### आवश्यकताएँ

- Eclipse IDE (2023-03 या बाद का संस्करण अनुशंसित)
- Java SE 21 या बाद का संस्करण

### स्थापना

1. [Releases](https://github.com/misstouch-taro/nonbreakpoint/releases) से नवीनतम `.jar` फ़ाइल डाउनロードLoad करें
2. `.jar` फ़ाइल को अपने Eclipse इंस्टॉलेशन के `dropins` फ़ोल्डर में रखें
3. Eclipse पुनः प्रारंभ करें

### उपयोग

1. जिस पंक्ति को चिह्नित करना हो उस पर कर्सर रखें (या कई पंक्तियाँ चुनें)
2. `Ctrl + Shift + N` दबाएँ या राइट-क्लिक करके **Toggle Non-Breakpoint** चुनें
3. चिह्नित पंक्ति पर पुनः `Ctrl + Shift + N` दबाने से चिह्न हट जाता है

---

## License

MIT License

## Author

[misstouch-taro](https://github.com/misstouch-taro)
